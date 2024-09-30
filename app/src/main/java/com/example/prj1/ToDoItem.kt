package com.example.prj1

data class ToDoItem(
    val id: Int,
    val task: String,
    var isDone: Boolean = false
)
