package com.eeerrorcode.cookietodo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo (
    @PrimaryKey(autoGenerate = true)
    var num:Int = 0,
    var title: String ,
    var completed: Boolean
)