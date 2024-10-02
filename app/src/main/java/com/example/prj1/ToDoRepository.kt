package com.example.prj1

import android.util.Log

class ToDoRepository {
    private val toDoList = mutableListOf(
        ToDoItem(1, "Học Jetpack Compose"  ,true),
        ToDoItem(2, "Làm bài tập lập trình"),
        ToDoItem(3, "Đọc tài liệu về MVVM",true)
    )

    fun getToDoList() = toDoList.toList() // Trả về danh sách mới để không cho phép thay đổi bên ngoài


    fun addTask(task: ToDoItem) {
        toDoList.add(task)
    }

    fun toggleTaskCompletion(id: Int) {
        toDoList.find { it.id == id }?.let {
            it.isDone = !it.isDone
        }
    }

    fun removeTask(id: Int) {
        toDoList.removeAll{it.id == id}
    }

    fun sortTaskRe() {
        toDoList.sortBy { it.isDone }
    }

    fun editTask(id: Int, newTaskName: String) {
        toDoList.find { it.id == id }?.let {
            it.task = newTaskName // Cập nhật tên công việc
            Log.d("ToDoRepository", "Task edited: $id to $newTaskName")
        }
    }

}