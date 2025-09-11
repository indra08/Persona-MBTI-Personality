package com.insantech.personalitytest.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.insantech.personalitytest.data.AboutLinks

@Composable
fun AboutScreen(onBack: () -> Unit) {
    val ctx = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.height(16.dp))
        Text(
            "Tentang Aplikasi",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(12.dp))

        Text(
            "Aplikasi ini menggunakan butir gaya MBTI (bukan butir resmi MBTI®) "
                    + "untuk tujuan edukasi dan eksplorasi diri. "
                    + "Untuk alat open-source, tersedia IPIP (public domain).",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(Modifier.height(16.dp))

        // Daftar link dari AboutLinks.kt
        AboutLinks.forEach { link ->
            Text(
                text = "• " + link.title,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link.url))
                        ctx.startActivity(intent)
                    }
                    .padding(vertical = 4.dp)
            )
        }

        Spacer(Modifier.height(24.dp))

        OutlinedButton(onClick = onBack) {
            Text("Kembali")
        }
    }
}
