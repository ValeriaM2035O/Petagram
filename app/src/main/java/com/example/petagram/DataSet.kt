package com.example.petagram

object DataSet {
    fun obtenerMascotas(): MutableList<Mascota> = mutableListOf(
    Mascota("Catty", R.drawable.perro1, 5),
    Mascota("Ronny", R.drawable.perro2, 3),
    Mascota("Luna", R.drawable.perro3, 4),
    Mascota("Max", R.drawable.perro4, 2),
    Mascota("Bella", R.drawable.perro5, 1)
    )

    fun obtenerFavoritas(): MutableList<Mascota> = mutableListOf(
        Mascota("Ronny", R.drawable.perro2, 3),
        Mascota("Catty", R.drawable.perro1, 5),
        Mascota("Luna", R.drawable.perro3, 4),
        Mascota("Max", R.drawable.perro4, 2),
        Mascota("Bella", R.drawable.perro5, 1)
    )
}