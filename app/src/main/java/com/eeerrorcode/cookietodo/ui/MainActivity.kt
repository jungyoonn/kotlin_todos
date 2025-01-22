package com.eeerrorcode.cookietodo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eeerrorcode.cookietodo.R
import com.eeerrorcode.cookietodo.adapter.TodoAdapter
import com.eeerrorcode.cookietodo.data.TodoDatabase
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var todoDatabase: TodoDatabase
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoDatabase = TodoDatabase.getDatabase(this)

        // RecycleView 연결
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        todoAdapter = TodoAdapter { todo ->
            lifecycleScope.launch {
                todoDatabase.todoDao().update(todo.copy(completed = !todo.completed))
            }
        }

        recyclerView.adapter = todoAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun loadTodos() {
        lifecycleScope.launch {
            val todos = todoDatabase.todoDao().getAllTodos()
            todoAdapter.submitList(todos)
        }
    }
}