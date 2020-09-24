package com.kmpdroidcon.toyapp

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kmpdroidcon.todokmp.uimodel.TodoUiItem

class TodoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val timestamp: TextView = view.findViewById(R.id.item_timestamp)
    private val content: TextView = view.findViewById(R.id.item_content)

    fun bind(item: TodoUiItem) {
        timestamp.text = item.timestamp
        content.text = item.content
    }
}