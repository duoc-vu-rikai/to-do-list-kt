package com.example.prj1
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private val viewModel: ToDoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val taskInput: EditText = findViewById(R.id.et_task_input)
        val addButton: Button = findViewById(R.id.btn_add_task)
        val sortButton: Button = findViewById(R.id.btn_sort_task)
        val taskRecyclerView: RecyclerView = findViewById(R.id.rv_task_list)

        val adapter = ToDoAdapter(
            onTaskClick = { taskId ->
                viewModel.toggleTaskCompletion(taskId)
            },
            onDeleteClick = {   taskId ->
                viewModel.removeTask(taskId)
            }
        )
        taskRecyclerView.layoutManager = LinearLayoutManager(this)
        taskRecyclerView.adapter = adapter

        viewModel.toDoList.observe(this, Observer { toDoList ->
            adapter.submitList(toDoList)
        })

        addButton.setOnClickListener {
            val task = taskInput.text.toString()
            if (task.isNotEmpty()) {
                viewModel.addTask(task)
                taskInput.text.clear()
            }
        }

        sortButton.setOnClickListener{
            viewModel.sortTask()
        }

    }
}
