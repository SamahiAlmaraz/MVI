package com.example.mvi.ui.main.intent

sealed class MainIntent {
    object FetchTodoTasks: MainIntent()
}