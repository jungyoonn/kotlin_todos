package com.eeerrorcode.cookietodo.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eeerrorcode.cookietodo.R
import com.eeerrorcode.cookietodo.adapter.TodoAdapter
import com.eeerrorcode.cookietodo.data.TodoDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
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
                loadTodos()
            }
        }

        recyclerView.adapter = todoAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener{
            val intent = Intent(this, AddTodoActivity::class .java)
            startActivityForResult(intent, ADD_TODO_REQUEST_CODE)
        }

        findViewById<FloatingActionButton>(R.id.fab_web).setOnClickListener {
            val intent = Intent(this, WebActivity::class.java)
            startActivity(intent)
        }

        loadTodos()
    }

    private fun loadTodos() {
        lifecycleScope.launch {
            val todos = todoDatabase.todoDao().getAllTodos()
            todoAdapter.submitList(todos)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == ADD_TODO_REQUEST_CODE && resultCode == RESULT_OK)
            loadTodos()
    }

    companion object{
        private const val ADD_TODO_REQUEST_CODE = 1001
    }
}