package com.kmpdroidcon.toyapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kmpdroidcon.core.model.TodoItem
import com.kmpdroidcon.todokmp.TodoListViewModel

class MainActivity : AppCompatActivity() {

    // TODO inject viewModel here
    private lateinit var viewModel: TodoListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.todolist)
        val myAdapter = TodoListAdapter()

        // TODO removed and replace with real data coming from view model
        myAdapter.items = listOf(
            TodoItem(0, "Todo 1"),
            TodoItem(4, "Todo 2"),
        )

        recyclerView.apply {
            setHasFixedSize(false)
            val linerLayoutManager =
                LinearLayoutManager(context).apply { orientation = RecyclerView.VERTICAL }
            layoutManager = linerLayoutManager
            adapter = myAdapter
        }
    }
}
