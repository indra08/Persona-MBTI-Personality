package com.insantech.personalitytest.logic


import com.insantech.personalitytest.data.Dimension
import com.insantech.personalitytest.data.Question

// Pemetaan arah butir: true = mendukung sisi pertama di tiap dikotomi (E, S, T, J)
private val positiveTowardFirst = setOf(1,3,5,7,9, 11,13,15,17,19, 21,23,25,27,29, 31,33,35,37,39)

fun scoreMbti(responses: Map<Int, Int>, items: List<Question>): String {
    var e = 0; var i = 0
    var s = 0; var n = 0
    var t = 0; var f = 0
    var j = 0; var p = 0

    items.forEach { q ->
        val raw = responses[q.id] ?: 3 // default netral jika kosong
        val value = raw.coerceIn(1,5)
        val towardFirst = positiveTowardFirst.contains(q.id)
        when (q.dimension) {
            Dimension.EI -> if (towardFirst) { e += value; i += (6 - value) } else { i += value; e += (6 - value) }
            Dimension.SN -> if (towardFirst) { s += value; n += (6 - value) } else { n += value; s += (6 - value) }
            Dimension.TF -> if (towardFirst) { t += value; f += (6 - value) } else { f += value; t += (6 - value) }
            Dimension.JP -> if (towardFirst) { j += value; p += (6 - value) } else { p += value; j += (6 - value) }
        }
    }

    val type = buildString {
        append(if (e >= i) 'E' else 'I')
        append(if (s >= n) 'S' else 'N')
        append(if (t >= f) 'T' else 'F')
        append(if (j >= p) 'J' else 'P')
    }
    return type
}

fun describe(type: String): String = when (type) {
    "INTJ" -> "Arsitek strategi yang visioner, fokus pada perencanaan jangka panjang, dan suka menyusun sistem yang efisien. Mereka sering terlihat serius, mandiri, serta memiliki standar tinggi baik untuk diri sendiri maupun orang lain."
    "INTP" -> "Perancang ide yang penuh rasa ingin tahu, suka menganalisis konsep abstrak, dan tertarik menemukan pola di balik fenomena. Mereka cenderung fleksibel, kadang tampak santai, tetapi memiliki pemikiran logis yang mendalam."
    "ENTJ" -> "Komandan yang tegas, berorientasi tujuan, dan pandai mengorganisasi orang maupun sumber daya. Mereka senang mengambil keputusan cepat, penuh percaya diri, serta mampu memimpin dalam situasi penuh tantangan."
    "ENTP" -> "Wira ide yang kreatif, antusias, dan suka bereksperimen dengan kemungkinan baru. Mereka senang berdebat, menyukai perubahan, dan mudah memunculkan ide-ide segar yang seringkali mengejutkan."
    "INFJ" -> "Penasihat yang empatik, visioner, dan berpegang teguh pada nilai pribadi. Mereka sering berperan sebagai pendengar yang baik, peduli pada kesejahteraan orang lain, serta mampu melihat gambaran besar di balik detail."
    "INFP" -> "Idealistis yang digerakkan oleh nilai pribadi, imajinatif, dan tenang. Mereka mencari makna dalam hubungan maupun pekerjaan, sering mengekspresikan diri lewat seni atau tulisan, serta menghargai harmoni batin."
    "ENFJ" -> "Penggerak sosial yang hangat, karismatik, dan memimpin dengan empati. Mereka mudah membangun hubungan, peka terhadap kebutuhan kelompok, serta bersemangat memotivasi orang lain untuk mencapai potensi terbaiknya."
    "ENFP" -> "Inspirator yang spontan, penuh energi, dan sangat kreatif. Mereka menikmati interaksi sosial yang hangat, mudah antusias pada ide-ide baru, serta menginspirasi orang lain dengan optimisme dan semangat hidup."
    "ISTJ" -> "Pengelola yang praktis, teliti, dan bertanggung jawab. Mereka menyukai keteraturan, setia pada kewajiban, serta dapat diandalkan dalam menyelesaikan tugas dengan cara yang efisien dan konsisten."
    "ISFJ" -> "Pelindung yang penuh dedikasi, setia, dan peduli pada kebutuhan orang lain. Mereka memperhatikan detail, menjaga harmoni, serta memiliki kepekaan tinggi terhadap perasaan lingkungan sekitarnya."
    "ESTJ" -> "Eksekutor yang terorganisasi, logis, dan tegas dalam bertindak. Mereka suka membuat struktur yang jelas, memimpin dengan sistem, serta memastikan setiap pekerjaan dilakukan secara efisien sesuai aturan."
    "ESFJ" -> "Penyokong yang ramah, kooperatif, dan berorientasi pada hubungan sosial. Mereka senang menjaga keharmonisan kelompok, rela berkorban untuk orang lain, serta merasa puas ketika dapat membantu sesama."
    "ISTP" -> "Praktisi yang tenang, realistis, dan gesit dalam memecahkan masalah teknis. Mereka menikmati bekerja dengan tangan atau alat, adaptif terhadap situasi baru, serta sering mencari solusi praktis yang cepat."
    "ISFP" -> "Seniman yang sensitif, ekspresif, dan sangat menghargai keindahan. Mereka suka kebebasan untuk menjelajah kreativitas, fleksibel terhadap perubahan, serta cenderung hidup di saat ini dengan penuh apresiasi."
    "ESTP" -> "Aktor lapangan yang energik, spontan, dan menyukai aksi langsung. Mereka nyaman mengambil risiko, cepat tanggap dalam situasi darurat, serta memiliki kemampuan alami untuk memimpin dalam kondisi dinamis."
    "ESFP" -> "Entertainer yang ekspresif, penuh humor, dan hidup untuk momen sekarang. Mereka suka berinteraksi dengan banyak orang, menikmati pengalaman baru, serta membawa keceriaan dan energi positif ke lingkungannya."
    else -> "Ringkasan tidak tersedia."
}