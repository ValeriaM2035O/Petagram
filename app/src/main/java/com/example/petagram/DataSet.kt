package com.example.petagram


object DataSet {
    fun obtenerMascotas(): MutableList<Mascota> = mutableListOf(
        Mascota(1, "Catty", R.drawable.perro1, 5),
        Mascota(2, "Ronny", R.drawable.perro2, 3),
        Mascota(3, "Luna", R.drawable.perro3, 4),
        Mascota(4, "Max", R.drawable.perro4, 2),
        Mascota(5, "Bella", R.drawable.perro5, 1),
        Mascota(6, "Rocky", R.drawable.perro1, 0),
        Mascota(7, "Toby", R.drawable.perro2, 0)
    )

    fun obtenerFotosPerfil(): List<FotoPerfil> = listOf(
        FotoPerfil(R.drawable.perro2, 5),
        FotoPerfil(R.drawable.perro2, 0),
        FotoPerfil(R.drawable.perro3, 3),
        FotoPerfil(R.drawable.perro1, 10),
        FotoPerfil(R.drawable.perro4, 2),
        FotoPerfil(R.drawable.perro5, 3),
        FotoPerfil(R.drawable.perro2, 4),
        FotoPerfil(R.drawable.perro3, 1),
        FotoPerfil(R.drawable.perro4, 7)
    )
}