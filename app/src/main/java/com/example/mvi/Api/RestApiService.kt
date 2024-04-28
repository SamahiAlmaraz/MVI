package com.example.mvi.Api

import com.example.mvi.Model.TodoTask
import retrofit2.http.GET

interface RestApiService {
    @GET("todos")
    suspend fun listTodo(): List<TodoTask>
}