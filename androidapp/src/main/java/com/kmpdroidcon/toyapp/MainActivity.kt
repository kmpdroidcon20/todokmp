package com.kmpdroidcon.toyapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kmpdroidcon.todokmp.TodoListViewModel
import com.kmpdroidcon.todokmp.TodoListViewModelImpl

class MainActivity : AppCompatActivity() {

    // TODO inject viewModel here
    private val viewModel: TodoListViewModel = TodoListViewModelImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.todolist)
        val createTodoButton = findViewById<Button>(R.id.create_todo_button)
        val editText = findViewById<EditText>(R.id.todo_content_edittext)
        createTodoButton.setOnClickListener {
            viewModel.createTodo(editText.text.toString())
        }
        val myAdapter = TodoListAdapter()

        recyclerView.apply {
            setHasFixedSize(false)
            val linerLayoutManager =
                LinearLayoutManager(context).apply { orientation = RecyclerView.VERTICAL }
            layoutManager = linerLayoutManager
            adapter = myAdapter
        }

        viewModel.todoStream.subscribe {
            // TODO if we want let's optimize to avoid full data invalidation
            myAdapter.items = it
        }

    }
}
