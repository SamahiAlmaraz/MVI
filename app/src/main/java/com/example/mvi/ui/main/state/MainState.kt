package com.example.mvi.ui.main.state

import com.example.mvi.Model.TodoTask

sealed class MainState {
    object Idle: MainState()
    object Loading: MainState()
    data class LoadTasks(val todoTasks: List<TodoTask>): MainState()
    data class Error(val error: String?): MainState()
}