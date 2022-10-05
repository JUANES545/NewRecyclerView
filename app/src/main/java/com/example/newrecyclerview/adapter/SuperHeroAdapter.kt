package com.example.newrecyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newrecyclerview.R
import com.example.newrecyclerview.SuperHero
import com.example.newrecyclerview.databinding.ItemSuperheroBinding
import kotlin.coroutines.coroutineContext

class SuperHeroAdapter(
    private val superheroList: List<SuperHero>,
    private val onClickListener: (SuperHero) -> Unit,
    private val onClickDelete: (Int) -> Unit
) : RecyclerView.Adapter<SuperHeroViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return true
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SuperHeroViewHolder(layoutInflater.inflate(R.layout.item_superhero, parent, false))
    }

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        val item = superheroList[position]
        holder.render(item, onClickListener, onClickDelete) // to send data to selected item
    }

    override fun getItemCount(): Int = superheroList.size
}

class SuperHeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemSuperheroBinding.bind(view)

    fun render(
        superHeroModel: SuperHero,
        onClickListener: (SuperHero) -> Unit,
        onClickDelete: (Int) -> Unit
    ) {
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
            binding.btnDelete.setOnClickListener { onClickDelete(adapterPosition) }

        }
    }
}
