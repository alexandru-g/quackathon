package com.sv.quackathon.model

data class Image(
    val id: String,
    val imageResource: String,
    val labels: List<String> = listOf(),
    val comments: List<String> = listOf(),
    val likesCount: Int,
    val dislikesCount: Int
)