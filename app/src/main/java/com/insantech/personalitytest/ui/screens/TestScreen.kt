package com.insantech.personalitytest.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.insantech.personalitytest.data.Question

@Composable
fun TestScreen(
    items: List<Question>,
    onSubmit: () -> Unit,
    onAnswer: (id: Int, value: Int) -> Unit
) {
    var progress by remember { mutableIntStateOf(0) }

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        item {
            Spacer(Modifier.height(16.dp))
            Text("Skala 1–5: 1 Sangat Tidak Setuju • 5 Sangat Setuju", style = MaterialTheme.typography.bodyLarge)
            Spacer(Modifier.height(8.dp))
        }
        items(items) { q ->
            QuestionCard(q) { value ->
                onAnswer(q.id, value)
                progress = progress + 1
            }
            Spacer(Modifier.height(8.dp))
        }
        item {
            Button(onClick = onSubmit, modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)) {
                Text("Lihat Hasil")
            }
        }
    }
}

@Composable
private fun QuestionCard(q: Question, onSelect: (Int) -> Unit) {
    var selected by remember { mutableIntStateOf(0) }
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp)) {
            Text(q.text, style = MaterialTheme.typography.bodyLarge)
            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                (1..5).forEach { v ->
                    FilterChip(
                        selected = selected == v,
                        onClick = { selected = v; onSelect(v) },
                        label = { Text(v.toString()) }
                    )
                }
            }
        }
    }
}