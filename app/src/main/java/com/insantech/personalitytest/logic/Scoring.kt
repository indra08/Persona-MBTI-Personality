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
    "INTJ" -> "Arsitek strategi: analitis, visioner, terstruktur."
    "INTP" -> "Perancang ide: logis, ingin tahu, konseptual."
    "ENTJ" -> "Komandan: tegas, organisator, berorientasi tujuan."
    "ENTP" -> "Wira ide: cepat, inovatif, debat antusias."
    "INFJ" -> "Penasihat: empatik, berprinsip, berwawasan."
    "INFP" -> "Idealistis: penuh nilai, imajinatif, tenang."
    "ENFJ" -> "Penggerak sosial: hangat, memimpin dengan empati."
    "ENFP" -> "Inspirator: kreatif, spontan, berenergi."
    "ISTJ" -> "Pengelola: teliti, dapat diandalkan, praktis."
    "ISFJ" -> "Pelindung: setia, peduli, detail."
    "ESTJ" -> "Eksekutor: efisien, memimpin dengan sistem."
    "ESFJ" -> "Penyokong: ramah, harmonis, kooperatif."
    "ISTP" -> "Praktisi: tenang, problem-solver, taktis."
    "ISFP" -> "Seniman: sensitif, adaptif, apresiatif."
    "ESTP" -> "Aktor lapangan: gesit, langsung, berani."
    "ESFP" -> "Entertainer: spontan, ekspresif, menikmati momen."
    else -> "Ringkasan tidak tersedia."
}