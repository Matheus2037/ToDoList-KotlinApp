package br.edu.satc.todolistcompose

import androidx.room.Entity

@Entity
data class TaskData (
    val title: String,
    val description: String,
    val complete: Boolean
)