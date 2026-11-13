package com.example.petagram

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MascotaAdapter(private val mascotas: List<Mascota>) :
    RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder>() {

    class MascotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgFoto: ImageView = itemView.findViewById(R.id.imgFoto)
        val btnLike: ImageButton = itemView.findViewById(R.id.btnLike)
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombre)
        val tvRating: TextView = itemView.findViewById(R.id.tvRating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MascotaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_mascota, parent, false)
        return MascotaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MascotaViewHolder, position: Int) {
        val mascota = mascotas[position]
        holder.imgFoto.setImageResource(mascota.foto)
        holder.tvNombre.text = mascota.nombre
        holder.tvRating.text = mascota.rating.toString()

        // Hueso blanco = dar like: suma 1 al rating (hueso amarillo)
        holder.btnLike.setOnClickListener {
            mascota.rating++
            notifyItemChanged(holder.bindingAdapterPosition)
        }
    }

    override fun getItemCount() = mascotas.size
}