package com.example.petagram

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar

class FavoritasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritas)

        // Flecha para regresar al Activity padre
        findViewById<MaterialToolbar>(R.id.toolbarFav)
            .setNavigationOnClickListener { finish() }

        val rv = findViewById<RecyclerView>(R.id.rvFavoritas)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = MascotaAdapter(BaseDatos(this).obtenerUltimas5())
    }
}