package com.example.petagram

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FotoAdapter(private val fotos: List<FotoPerfil>) :
    RecyclerView.Adapter<FotoAdapter.FotoViewHolder>() {

    class FotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.imgFotoPerfil)
        val tvRating: TextView = itemView.findViewById(R.id.tvRatingFoto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FotoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_foto_perfil, parent, false)
        return FotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: FotoViewHolder, position: Int) {
        holder.img.setImageResource(fotos[position].foto)
        holder.tvRating.text = fotos[position].rating.toString()
    }

    override fun getItemCount() = fotos.size
}