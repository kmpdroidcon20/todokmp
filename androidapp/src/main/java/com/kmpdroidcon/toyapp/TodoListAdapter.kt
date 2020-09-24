package com.kmpdroidcon.toyapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kmpdroidcon.todokmp.uimodel.TodoUiItem

class TodoListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // TODO use UI model?
    private var itemList: MutableList<TodoUiItem> = mutableListOf()
    var items: List<TodoUiItem>
        set(value) {
            itemList.apply {
                clear()
                addAll(value)
            }
            notifyDataSetChanged()
        }
        get() = itemList

    private fun inflateView(parent: ViewGroup, layoutRes: Int): View =
        LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = inflateView(parent, R.layout.todoitem)
        return TodoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = itemList[position]
        val todoViewHolder = holder as TodoViewHolder
        todoViewHolder.bind(item)
    }
}
