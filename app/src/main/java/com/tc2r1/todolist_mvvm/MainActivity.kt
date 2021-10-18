package com.tc2r1.todolist_mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var todoAdapter : ToDoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = ToDoAdapter(mutableListOf())

        rv_todo_items.adapter = todoAdapter
        rv_todo_items.layoutManager = LinearLayoutManager(this)

        btn_add_todo.setOnClickListener {
            val todoTitle = et_todo_title.text.toString()
            if(todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                et_todo_title.text.clear()
            }
        }

        btn_delete_done_todo.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }
    }
}