package com.example.prj1

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ToDoAdapter(
    private val onTaskClick: (Int) -> Unit ,
    private val onDeleteClick: (Int) -> Unit ,
    private val onEditClick: (ToDoItem) -> Unit ) :
    ListAdapter<ToDoItem, ToDoAdapter.ToDoViewHolder>(ToDoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return ToDoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val task = getItem(position)
        holder.bind(task, onTaskClick , onDeleteClick, onEditClick)

        holder.itemView.findViewById<Button>(R.id.btnRemove).setOnClickListener{
            onDeleteClick(task.id)
        }

    }

    class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val taskName: TextView = itemView.findViewById(R.id.tv_task_name)
        private val taskCheckBox: CheckBox = itemView.findViewById(R.id.cb_task_done)
        private val removeButton: Button = itemView.findViewById(R.id.btnRemove)

        fun bind(task: ToDoItem, onTaskClick: (Int) -> Unit , onDeleteClick: (Int) -> Unit ,onEditClick: (ToDoItem) -> Unit) {
            taskName.text = task.task
            taskCheckBox.isChecked = task.isDone
            taskCheckBox.setOnClickListener {
                onTaskClick(task.id)
            }
            removeButton.setOnClickListener{
                onDeleteClick(task.id)

            }

            itemView.setOnLongClickListener(){
                onEditClick(task)
                true
            }
        }
    }

    class ToDoDiffCallback : DiffUtil.ItemCallback<ToDoItem>() {
        override fun areItemsTheSame(oldItem: ToDoItem, newItem: ToDoItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ToDoItem, newItem: ToDoItem): Boolean {
            return oldItem == newItem
        }
    }
}
