package com.example.petagram

data class Mascota(
    val id: Int,
    val nombre: String,
    val foto: Int,
    var rating: Int = 0,
    var ultimoLike: Long = 0
)
