package com.insantech.personalitytest.ui.screens

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.view.drawToBitmap
import com.insantech.personalitytest.util.ShareResultImage

@Composable
fun ResultScreen(
    types: String,                // tipe 4 huruf, mis. "INTJ"
    description: String,
    onRestart: () -> Unit
) {
    val ctx = LocalContext.current
    val view = LocalView.current

    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Persona Anda", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(12.dp))

        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp)) {
                val illRes = typeToIllustrationRes(types)
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .align(Alignment.CenterHorizontally)
                    , // background untuk area kosong
                    contentAlignment = Alignment.Center,
                ) {
                    Image(
                        painter = painterResource(id = illRes),
                        contentDescription = "Ilustrasi tipe $types",
                        modifier = Modifier.fillMaxHeight().width(200.dp),
                        contentScale = ContentScale.Fit
                    )
                }
                Spacer(Modifier.height(12.dp))

                Text(types, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                Text(description, style = MaterialTheme.typography.bodyLarge)
                Spacer(Modifier.height(12.dp))
                Text(
                    "Catatan: Ini adalah tes gaya MBTI yang tidak menggantikan asesmen resmi.",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(
                onClick = {
                    // Capture seluruh layar activity (cepat & praktis).
                    // Jika ingin hanya kartu: kita bisa render offscreen/khusus card (bisa kubuatin jika perlu).
                    val bitmap: Bitmap = view.drawToBitmap()
                    ShareResultImage(ctx, bitmap)   // fungsi media-share yang sudah kamu buat
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Bagikan Hasil")
            }

            OutlinedButton(
                onClick = onRestart,
                modifier = Modifier.weight(1f)
            ) {
                Text("Kembali")
            }
        }
    }
}
