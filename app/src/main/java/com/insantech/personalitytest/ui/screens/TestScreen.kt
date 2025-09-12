package com.insantech.personalitytest.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.insantech.personalitytest.data.Question
import kotlinx.coroutines.launch
import kotlin.math.ceil
import kotlin.math.min

@SuppressLint("UnrememberedMutableState")
@Composable
fun TestScreen(
    items: List<Question>,
    onAnswer: (Int, Int) -> Unit,
    onSubmit: () -> Unit
) {
    val pageSize = 5
    val totalPages = remember(items) { ceil(items.size / pageSize.toFloat()).toInt().coerceAtLeast(1) }
    var currentPage by remember { mutableStateOf(0) }

    // Simpan jawaban per pertanyaan
    val localSelections = remember { mutableStateMapOf<Int, Int>() }

    val start = currentPage * pageSize
    val end = min(start + pageSize, items.size)
    val pageItems = items.subList(start, end)

    val pageComplete by derivedStateOf {
        pageItems.all { q -> (localSelections[q.id] ?: 0) in 1..5 }
    }

    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header + progres
        Text(
            "Skala 1–5: 1 Sangat Tidak Setuju • 5 Sangat Setuju",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(Modifier.height(8.dp))
        LinearProgressIndicator(
            progress = (currentPage + 1f) / totalPages.toFloat(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        Text(
            "Halaman ${currentPage + 1} dari $totalPages",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(Modifier.height(12.dp))

        // Daftar pertanyaan (5 per halaman)
        LazyColumn(
            modifier = Modifier.weight(1f),
            state = listState
        ) {
            items(pageItems) { q ->
                QuestionCard(
                    question = q,
                    selected = localSelections[q.id] ?: 0,
                    onSelect = { value ->
                        localSelections[q.id] = value
                        onAnswer(q.id, value)
                    }
                )
                Spacer(Modifier.height(12.dp))
            }
        }

        // Navigasi
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(
                onClick = {
                    if (currentPage > 0) {
                        currentPage -= 1
                        scope.launch { listState.animateScrollToItem(0) }
                    }
                },
                enabled = currentPage > 0,
                modifier = Modifier.weight(1f)
            ) { Text("Sebelumnya") }

            if (currentPage < totalPages - 1) {
                Button(
                    onClick = {
                        if (currentPage < totalPages - 1) {
                            currentPage += 1
                            scope.launch { listState.animateScrollToItem(0) }
                        }
                    },
                    enabled = pageComplete,
                    modifier = Modifier.weight(1f)
                ) { Text("Berikutnya") }
            } else {
                Button(
                    onClick = onSubmit,
                    enabled = pageComplete,
                    modifier = Modifier.weight(1f)
                ) { Text("Lihat Hasil") }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun QuestionCard(
    question: Question,
    selected: Int,
    onSelect: (Int) -> Unit
) {
    ElevatedCard(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp)) {
            Text(question.text, style = MaterialTheme.typography.bodyLarge)
            Spacer(Modifier.height(10.dp))

            // Baris FilterChip 1..5
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                (1..5).forEach { v ->
                    FilterChip(
                        selected = selected == v,
                        onClick = { onSelect(v) },
                        label = { Text(v.toString()) }
                    )
                }
            }
        }
    }
}
