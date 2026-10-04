package com.example.a24012011128_mad_practical_7

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a24012011128_mad_practical_7.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: DatabaseHelper
    private lateinit var adapter: PersonAdapter
    private val persons = ArrayList<Person>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)
        adapter = PersonAdapter(persons) { person -> db.deletePerson(person) }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.fabRefresh.setOnClickListener { resetAndShowPersons() }

        resetAndShowPersons()
    }

    private fun showPersons() {
        persons.clear()
        persons.addAll(db.allPersons)
        adapter.notifyDataSetChanged()
    }

    private fun resetAndShowPersons() {
        db.deleteAll()
        val defaultPersons = arrayListOf(
            Person("1", "Shaurya", "shaurya13@gnu.ac.in", "+91 9876543210", "Ganpat University, Mehsana"),
            Person("2", "Aditya", "aditya14@gnu.ac.in", "+91 9876543211", "Ganpat University, Mehsana"),
            Person("3", "Rudra", "rudra15@gnu.ac.in", "+91 9876543212", "Ganpat University, Mehsana"),
            Person("4", "Krish", "krish16@gnu.ac.in", "+91 9876543213", "Ganpat University, Mehsana"),
            Person("5", "Vaidit", "vaidit17@gnu.ac.in", "+91 9876543214", "Ganpat University, Mehsana"),
            Person("6", "Om", "om18@gnu.ac.in", "+91 9876543215", "Ganpat University, Mehsana"),
            Person("7", "Vivan", "vivan19@gnu.ac.in", "+91 9876543216", "Ganpat University, Mehsana")
        )
        defaultPersons.forEach { db.insertPerson(it) }
        showPersons()
        Toast.makeText(this, "Persons list refreshed", Toast.LENGTH_SHORT).show()
    }
}
