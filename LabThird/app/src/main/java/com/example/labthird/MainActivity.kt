package com.example.labthird

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val items = listOf(
            ListItem.Header("Title 1"),
            ListItem.Item("Name 1", "Description 1"),
            ListItem.Item("Name 2", "Description 2"),
            ListItem.Header("Title 2"),
            ListItem.Item("Name 3", "Description 3")
        )

        recyclerView.adapter = MyAdapter(items)
    }
}