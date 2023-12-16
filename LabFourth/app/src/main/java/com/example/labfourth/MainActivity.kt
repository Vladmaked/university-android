package com.example.labfourth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        CoroutineScope(Dispatchers.IO).launch {
            if (database.listItemDao().getAll().isEmpty()) {
                addInitialDataToDatabase()
            }
            loadItemsFromDatabase()
        }
    }

    private fun loadItemsFromDatabase() {
        CoroutineScope(Dispatchers.IO).launch {
            val listItemEntities = database.listItemDao().getAll()
            val listItems = listItemEntities.map {
                when (it.type) {
                    "Header" -> ListItem.Header(it.title)
                    "Item" -> ListItem.Item(it.title, it.description ?: "")
                    else -> throw IllegalStateException("Unknown type")
                }
            }

            withContext(Dispatchers.Main) {
                recyclerView.adapter = MyAdapter(listItems)
            }
        }
    }

    private fun addInitialDataToDatabase() {
        val initialData = listOf(
            ListItemEntity(type = "Header", title = "Title 1"),
            ListItemEntity(type = "Item", title = "Name 1", description = "Description 1"),
            ListItemEntity(type = "Item", title = "Name 2", description = "Description 2"),
            ListItemEntity(type = "Header", title = "Title 2"),
            ListItemEntity(type = "Item", title = "Name 3", description = "Description 3"),
        )
        database.listItemDao().insertAll(*initialData.toTypedArray())
    }
}