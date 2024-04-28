package com.example.mvi.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvi.R
import com.example.mvi.Api.RestApiImpl
import com.example.mvi.Api.RetrofitBuilder
import com.example.mvi.Model.TodoTask
import com.example.mvi.ui.main.intent.MainIntent
import com.example.mvi.ui.main.state.MainState

import kotlinx.android.synthetic.main.activity_main.*

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val mainActivityViewModel: MainActivityViewModel by viewModels { MainViewModelFactory(RestApiImpl(RetrofitBuilder.apiService))}
    private var mainAdapter = MainAdapter(listOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUI()
        setupClicks()
        observeViewModel()
    }

    private fun setupClicks(){
        button_tasks.setOnClickListener {
            lifecycleScope.launch {
                mainActivityViewModel.userIntent.send(MainIntent.FetchTodoTasks)
            }
        }
    }

    private fun setupUI(){
        recyclerview_tasks.layoutManager = LinearLayoutManager(this)

        recyclerview_tasks.run {
            adapter = mainAdapter
        }
    }

    private fun observeViewModel(){
        lifecycleScope.launch {
            mainActivityViewModel.mainState.collect {mainState->
                when(mainState){
                    is MainState.Loading -> progressbar.visibility = View.VISIBLE

                    is MainState.LoadTasks -> {
                        progressbar.visibility = View.GONE
                        renderList(mainState.todoTasks)
                    }

                    is MainState.Error -> {
                        recyclerview_tasks.visibility = View.GONE
                        Toast.makeText(applicationContext, "Error: ${mainState.error}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun renderList(listTodoTask: List<TodoTask>){
        mainAdapter.setTodoTasks(listTodoTask)
        mainAdapter.notifyDataSetChanged()
    }
}