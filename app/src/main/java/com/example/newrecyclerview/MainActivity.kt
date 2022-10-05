package com.example.newrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newrecyclerview.adapter.SuperHeroAdapter
import com.example.newrecyclerview.databinding.ActivityMainBinding
import com.example.newrecyclerview.databinding.PopUpBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var binding2: PopUpBinding
    private var superHeroMutableList: MutableList<SuperHero> =
        SuperHeroProvider.superheroList.toMutableList()
    private lateinit var adapter: SuperHeroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        // Log.i("PERRO", superHeroMutableList.toString())
        adapter = SuperHeroAdapter(superHeroMutableList,
            onClickListener = { superHero -> onItemSelected(superHero) },
            onClickDelete = { position -> onDeletedItem(position) })
        with(binding) {
            recyclerSuperhero.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerSuperhero.adapter = adapter
        }
    }

    private fun onDeletedItem(position: Int) {

        superHeroMutableList.removeAt(position)
        adapter.notifyItemRemoved(position)

        // Temporally snackBar to restore information
        Snackbar.make(
            binding.root,
            "Item $position Deleted",
            Snackbar.LENGTH_SHORT
        ).apply {
            setAction("Undo"){
                MyUndoListener()
                // Assigned values
                binding2 = PopUpBinding.inflate(layoutInflater)
                val builder = AlertDialog.Builder(this@MainActivity)
                // View builder
                setContentView(binding2.root)
                val dialog = builder.create()
                dialog.show()

                // Button functionality
                binding2.btn.setOnClickListener {
                    Toast.makeText(this@MainActivity, "Prueba de botón, gracias", Toast.LENGTH_SHORT).show()
                }

                superHeroMutableList.add(superHeroMutableList[0])
            }
            show()
        }
    }

    private fun onRestoredItem(position: Int) {
        adapter.notifyItemRemoved(position)
    }

/*    private fun restoreItem(item: String?, position: Int) {
        superHeroMutableList.add(position, item)
        adapter.notifyItemInserted(position)
    }*/

    private fun onItemSelected(superHero: SuperHero) {
        Toast.makeText(this, superHero.superhero, Toast.LENGTH_SHORT).show()
    }
}