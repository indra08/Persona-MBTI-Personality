package com.insantech.personalitytest.data;

data class Question(
        val id: Int,
        val text: String,
        val dimension: Dimension
)

enum class Dimension { EI, SN, TF, JP }
