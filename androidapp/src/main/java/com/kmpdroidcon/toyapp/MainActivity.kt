package com.kmpdroidcon.toyapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kmpdroidcon.todokmp.TodoListViewModel
import com.kmpdroidcon.todokmp.dependency.PlatformDependency
import com.kmpdroidcon.todokmp.di.DIGraphImpl
import com.kmpdroidcon.todokmp.uimodel.TodoUiItem

class MainActivity : AppCompatActivity() {

    // TODO inject viewModel here
    private lateinit var viewModel: TodoListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = DIGraphImpl().build(PlatformDependency(applicationContext))
        viewModel.initialize()

        val recyclerView = findViewById<RecyclerView>(R.id.todolist)
        val createTodoButton = findViewById<Button>(R.id.create_todo_button)
        val editText = findViewById<EditText>(R.id.todo_content_edittext)
        createTodoButton.setOnClickListener {
            viewModel.createTodo(editText.text.toString())
        }
        val myAdapter = TodoListAdapter()

        // FIXME, this is just to show the issue please remove
        val list = listOf(TodoUiItem("13245L", "ewrwe"))
        println(list[0].content)

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

    override fun onDestroy() {
        viewModel.destroy()
        super.onDestroy()
    }
}
