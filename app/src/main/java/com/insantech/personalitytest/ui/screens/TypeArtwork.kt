package com.insantech.personalitytest.ui.screens

import com.insantech.personalitytest.R

fun typeToIllustrationRes(type: String): Int {
    return when (type.uppercase()) {
        "INTJ" -> R.drawable.ill_intj
        "INTP" -> R.drawable.ill_intp
        "ENTJ" -> R.drawable.ill_entj
        "ENTP" -> R.drawable.ill_entp
        "INFJ" -> R.drawable.ill_infj
        "INFP" -> R.drawable.ill_infp
        "ENFJ" -> R.drawable.ill_enfj
        "ENFP" -> R.drawable.ill_enfp
        "ISTJ" -> R.drawable.ill_istj
        "ISFJ" -> R.drawable.ill_isfj
        "ESTJ" -> R.drawable.ill_estj
        "ESFJ" -> R.drawable.ill_esfj
        "ISTP" -> R.drawable.ill_istp
        "ISFP" -> R.drawable.ill_isfp
        "ESTP" -> R.drawable.ill_estp
        "ESFP" -> R.drawable.ill_esfp
        else   -> R.drawable.ill_default
    }
}
