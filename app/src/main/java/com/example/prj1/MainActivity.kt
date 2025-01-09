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
import androidx.appcompat.app.AlertDialog

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
            },
            onEditClick = {
                    task ->
                showEditTaskDialog(task)
            }
        )
        taskRecyclerView.layoutManager = LinearLayoutManager(this)
        taskRecyclerView.adapter = adapter

        viewModel.toDoList.observe(this, Observer { toDoList ->
            adapter.submitList(toDoList)
            Log.d("MainActivity", "ToDoList updated: $toDoList") // Log khi danh sách được cập nhật
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

    private fun showEditTaskDialog(task: ToDoItem) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_edit_task, null)
        val editText = dialogView.findViewById<EditText>(R.id.et_edit_task)
        editText.setText(task.task)

        val dialog = AlertDialog.Builder(this)
            .setTitle("Chỉnh sửa công việc")
            .setView(dialogView)
            .setPositiveButton("Lưu") { _, _ ->
                val newTaskName = editText.text.toString()
                if (newTaskName.isNotEmpty()) {
                    viewModel.editTask(task.id, newTaskName)
                }
            }
            .setNegativeButton("Hủy", null)
            .create()

        dialog.show()
    }
}
