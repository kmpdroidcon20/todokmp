package com.kmpdroidcon.toyapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kmpdroidcon.todokmp.TodoListViewModel
import com.kmpdroidcon.todokmp.uimodel.TodoUiItem

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
            TodoUiItem("12/12/2020 12:40", "Todo 1"),
            TodoUiItem("12/12/2020 12:50", "Todo 2"),
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
