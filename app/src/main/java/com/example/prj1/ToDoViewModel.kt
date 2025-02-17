package com.example.prj1

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ToDoViewModel : ViewModel() {
    val repository = ToDoRepository()
    var lis = repository.getToDoList()
    private val _toDoList = MutableLiveData<List<ToDoItem>>()
    val toDoList: LiveData<List<ToDoItem>> = _toDoList

    init {
        _toDoList.value = repository.getToDoList()
    }

    fun addTask(task: String) {
        val newTask = ToDoItem(id = _toDoList.value?.size?.plus(1) ?: 1, task = task)
        repository.addTask(newTask)
        _toDoList.value = repository.getToDoList()
    }

    fun removeTask(id: Int) {
        repository.removeTask(id)
        _toDoList.value = repository.getToDoList()
    }

    fun toggleTaskCompletion(id: Int) {
        repository.toggleTaskCompletion(id)
        _toDoList.value = repository.getToDoList()
    }

    fun sortTask() {
        repository.sortTaskRe()
        _toDoList.value = repository.getToDoList() // Hoặc bạn có thể gán trực tiếp như sau
        _toDoList.value = repository.getToDoList().toList() // Đảm bảo là một danh sách mới
    }

}
