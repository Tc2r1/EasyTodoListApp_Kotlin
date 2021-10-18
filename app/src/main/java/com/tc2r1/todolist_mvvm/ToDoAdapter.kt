package com.tc2r1.todolist_mvvm

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class ToDoAdapter(private val todoList: MutableList<Todo>) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return ToDoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val currentItem = todoList[position]
        holder.itemView.apply {
            tv_todo_title.text = currentItem.title
            cb_done.isChecked = currentItem.isChecked
            toggleStrikeThrough(tv_todo_title, currentItem.isChecked)

            cb_done.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tv_todo_title, isChecked)
                currentItem.isChecked = !currentItem.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun addTodo(todo: Todo) {
        todoList.add(todo)
        notifyItemInserted(todoList.size - 1)
    }

    fun deleteDoneTodos() {
        todoList.removeAll { todo -> todo.isChecked }
        notifyDataSetChanged()
    }
    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
        if(isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}