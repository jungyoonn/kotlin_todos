package com.eeerrorcode.cookietodo.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.eeerrorcode.cookietodo.R
import com.eeerrorcode.cookietodo.data.Todo
import com.eeerrorcode.cookietodo.data.TodoDatabase
import kotlinx.coroutines.launch

class AddTodoActivity: AppCompatActivity() {
    private lateinit var todoDatabase: TodoDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_todo_activity)

        todoDatabase = TodoDatabase.getDatabase(this)

        val inputTitle = findViewById<EditText>(R.id.inputTitle)
        val btnAdd = findViewById<Button>(R.id.btnAdd)

        btnAdd.setOnClickListener {
            val title = inputTitle.text.toString()
            if(title.isNotBlank()) {
                lifecycleScope.launch {
                    val newTodo = Todo(title = title)
                    todoDatabase.todoDao().insert(newTodo)

                    setResult(RESULT_OK)
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    finish()
                }
            } else {
                inputTitle.error = "필수 입력입니다"
            }
        }
    }
}