package com.insantech.personalitytest.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ResultScreen(type: String, description: String, onRestart: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Persona Anda", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(12.dp))
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp)) {
                Text(type, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                Text(description, style = MaterialTheme.typography.bodyLarge)
                Spacer(Modifier.height(12.dp))
                Text("Catatan: Ini adalah tes gaya MBTI yang tidak menggantikan asesmen resmi.", style = MaterialTheme.typography.bodyLarge)
            }
        }
        Spacer(Modifier.height(24.dp))
        Button(onClick = onRestart, modifier = Modifier.fillMaxWidth()) { Text("Kembali ke Beranda") }
    }
}