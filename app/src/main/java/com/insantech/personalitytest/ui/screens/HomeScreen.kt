package com.insantech.personalitytest.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.insantech.personalitytest.R

@Composable
fun HomeScreen(onStart: () -> Unit, onAbout: () -> Unit, onLastResult: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(12.dp))
        Image(painterResource(id = R.drawable.ic_mbti_clean), contentDescription = null, modifier = Modifier.size(268.dp))
        Text(
            text = "Persona (MBTI)",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Jawab 40 pernyataan untuk melihat kecenderungan preferensi Anda pada empat dimensi: E–I, S–N, T–F, J–P.",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )

        Button(onClick = onStart, modifier = Modifier.fillMaxWidth()) { Text("Mulai Tes") }

        OutlinedButton(
            onClick = onLastResult,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Lihat Hasil Terakhir")
        }

        OutlinedButton(
            onClick = onAbout,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Tentang")
        }

        Spacer(Modifier.height(16.dp))
    }
}