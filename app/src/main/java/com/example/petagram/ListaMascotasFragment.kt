package com.example.petagram

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListaMascotasFragment : Fragment(R.layout.fragment_lista_mascotas) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mascotas = DataSet.obtenerMascotas()

        // Si la mascota ya tiene datos guardados, se usan esos
        val guardadas = BaseDatos(requireContext()).obtenerUltimas5().associateBy { it.id }
        mascotas.forEach { m ->
            guardadas[m.id]?.let {
                m.rating = it.rating
                m.ultimoLike = it.ultimoLike
            }
        }

        val rv = view.findViewById<RecyclerView>(R.id.rvMascotas)
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = MascotaAdapter(mascotas)
    }
}