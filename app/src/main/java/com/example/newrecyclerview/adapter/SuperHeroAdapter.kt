package com.example.newrecyclerview.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newrecyclerview.SuperHero

class SuperHeroAdapter(val superheroList:List<SuperHero>) : RecyclerView.Adapter<SuperHeroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = superheroList.size
}

class SuperHeroViewHolder : RecyclerView.ViewHolder(){

}
