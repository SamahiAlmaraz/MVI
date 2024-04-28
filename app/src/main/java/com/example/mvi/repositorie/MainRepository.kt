package com.example.mvi.repositorie

import com.example.mvi.Api.RestApi


class MainRepository(private val restApi: RestApi) {

    suspend fun getTodoTasks() = restApi.getTodoTasks()
}
