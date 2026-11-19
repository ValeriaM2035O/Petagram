package com.example.petagram

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Toolbar: estrella + menú de opciones
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        toolbar.inflateMenu(R.menu.menu_main)

        toolbar.menu.findItem(R.id.action_favoritos).actionView?.setOnClickListener {
            startActivity(Intent(this, FavoritasActivity::class.java))
        }

        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_contacto -> {
                    startActivity(Intent(this, ContactoActivity::class.java))
                    true
                }
                R.id.action_acerca -> {
                    startActivity(Intent(this, AcercaDeActivity::class.java))
                    true
                }
                else -> false
            }
        }

        // ViewPager2 + TabLayout con los dos Fragments
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        viewPager.adapter = PagerAdapter(this)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(if (position == 0) R.drawable.ic_home else R.drawable.ic_paw)
        }.attach()

        // Botón para subir foto
        findViewById<FloatingActionButton>(R.id.fabCamara).setOnClickListener {
            Toast.makeText(this, "Subir foto (próximamente)", Toast.LENGTH_SHORT).show()
        }
    }
}