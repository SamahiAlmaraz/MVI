package com.example.mvi.Api

import com.example.mvi.Model.TodoTask

class RestApiImpl(apiService: RestApiService) {

    class RestApiImpl(private val restApiService: RestApiService): RestApi {

        override suspend fun getTodoTasks(): List<TodoTask> {
            return restApiService.listTodo()
        }
    }
}