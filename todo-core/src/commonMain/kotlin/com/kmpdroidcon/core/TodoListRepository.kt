package com.kmpdroidcon.core

interface TodoListRepository {
    fun addTodo(item: String)
    fun getAll(): List<String>
}