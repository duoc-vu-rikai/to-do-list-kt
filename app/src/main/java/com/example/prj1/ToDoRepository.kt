package com.example.prj1

import android.util.Log

class ToDoRepository {
    private val toDoList = mutableListOf(
        ToDoItem(1, "Học Jetpack Compose", true),
        ToDoItem(2, "Làm bài tập lập trình"),
        ToDoItem(3, "Đọc tài liệu về MVVM", true),
        ToDoItem(4, "Đọc tài liệu về MVC", true)
    )

    fun getToDoList() =
        toDoList.toList() // Trả về danh sách mới để không cho phép thay đổi bên ngoài


    fun addTask(task: ToDoItem) {
        toDoList.add(task)
    }

    fun toggleTaskCompletion(id: Int) {
        toDoList.find { it.id == id }?.let {
            it.isDone = !it.isDone
        }
    }

    fun removeTask(id: Int) {
        toDoList.removeAll { it.id == id }
    }

    fun sortTaskRe() {
        toDoList.sortBy { it.isDone }
    }
}