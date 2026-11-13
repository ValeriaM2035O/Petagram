package com.example.petagram

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Toolbar con Action View de estrella
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.menu.findItem(R.id.action_favoritos).actionView?.setOnClickListener {
            startActivity(Intent(this, FavoritasActivity::class.java))
        }

        // RecyclerView
        val rv = findViewById<RecyclerView>(R.id.rvMascotas)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = MascotaAdapter(DataSet.obtenerMascotas())

        // Botón para subir
        findViewById<FloatingActionButton>(R.id.fabCamara).setOnClickListener {
            Toast.makeText(this, "Subir foto (próximamente)", Toast.LENGTH_SHORT).show()
        }
    }
}