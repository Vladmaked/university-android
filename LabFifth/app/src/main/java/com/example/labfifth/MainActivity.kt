package com.example.labfifth

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
            val quotesApi = RetrofitHelper.getInstance().create(QuotesApi::class.java)
            val response = quotesApi.getQuotes()
            if (response.isSuccessful && response.body() != null) {
                val results = response.body()!!.results
                val listItemEntities = results.map {
                    ListItemEntity(
                        type = "Item",
                        title = it.author,
                        description = it.content
                    )
                }
                database.listItemDao().insertAll(*listItemEntities.toTypedArray())
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
}