package com.eeerrorcode.cookietodo.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TodoDao {
    // CRUD

    @Insert
    fun insert(todo: Todo)

    @Query("select * from todo")
    fun getAllTodos()

    @Update
    fun update(todo: Todo)

    @Delete
    fun delete(todo: Todo)
}