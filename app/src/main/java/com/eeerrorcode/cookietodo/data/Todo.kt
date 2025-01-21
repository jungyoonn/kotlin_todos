package com.eeerrorcode.cookietodo.data

import androidx.room.Entity

@Entity
data class Todo (
    var num:Int = 0,
    var title: String ,
    var completed: Boolean
)