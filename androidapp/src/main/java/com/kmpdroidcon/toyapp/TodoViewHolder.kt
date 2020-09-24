package com.kmpdroidcon.toyapp

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kmpdroidcon.core.model.TodoItem

class TodoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val timestamp: TextView = view.findViewById(R.id.item_timestamp)
    private val content: TextView = view.findViewById(R.id.item_content)

    fun bind(item: TodoItem) {
        // TODO add Mvvm here or skip?
        timestamp.text = item.timestamp.toString() // TODO add parsing here, in MVVM?
        content.text = item.todo // TODO add parsing here, in MVVM?
    }
}