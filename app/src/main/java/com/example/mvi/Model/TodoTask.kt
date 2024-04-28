package com.example.mvi.Model

data class TodoTask(
    val userId: Long,
    val id: Long,
    val title: String,
    val completed: Boolean
)
{
}