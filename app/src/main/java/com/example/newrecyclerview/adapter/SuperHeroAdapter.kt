package com.example.newrecyclerview.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newrecyclerview.R
import com.example.newrecyclerview.SuperHero
import com.example.newrecyclerview.databinding.ItemSuperheroBinding

class SuperHeroAdapter(private val superheroList: List<SuperHero>, private val onClickListener:(SuperHero) -> Unit) :
    RecyclerView.Adapter<SuperHeroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SuperHeroViewHolder(layoutInflater.inflate(R.layout.item_superhero, parent, false))
    }

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        val item = superheroList[position]
        holder.render(item, onClickListener) // to send data to selected item
    }

    override fun getItemCount(): Int = superheroList.size
}

class SuperHeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemSuperheroBinding.bind(view)

    fun render(superHeroModel: SuperHero, onClickListener:(SuperHero) -> Unit) {
        with(binding) {
            tvSuperheroName.text = superHeroModel.superhero
            tvRealName.text = superHeroModel.realName
            tvPublisher.text = superHeroModel.publisher
            Glide.with(ivSuperhero.context).load(superHeroModel.photo)
                .into(binding.ivSuperhero) // to see images from web

            // Click listener complete item
            /**
            itemView.setOnClickListener { Toast.makeText(
                ivSuperhero.context,
                superHeroModel.superhero,
                Toast.LENGTH_SHORT
            ).show() }
             */

            itemView.setOnClickListener { onClickListener(superHeroModel) }

        }
    }
}
