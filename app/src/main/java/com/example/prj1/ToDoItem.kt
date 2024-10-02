package com.example.prj1

data class ToDoItem(
    val id: Int,
    var task: String,
    var isDone: Boolean = false
)
