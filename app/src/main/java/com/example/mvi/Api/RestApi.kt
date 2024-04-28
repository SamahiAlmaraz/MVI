package com.example.mvi.Api

import com.example.mvi.Model.TodoTask

interface RestApi {
    suspend fun getTodoTasks(): List<TodoTask>
}