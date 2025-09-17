package com.santig.auj_reto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _taskData = MutableStateFlow(Task())
    val taskData get() = _taskData

    private val _uiState = MutableStateFlow(UiState())
    val uiState get() = _uiState

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks get() = _tasks

    fun uiState(uiStates: UiState) {
        _uiState.value = uiState.value.copy(
            status = uiStates.status,
            loading = uiStates.loading,
            enabled = uiStates.enabled
        )
    }

    fun taskData(task: Task) {
        uiState(uiStates = uiState.value.copy(enabled = task.title.isNotEmpty() && task.description.isNotEmpty()))
        _taskData.value = taskData.value.copy(
            title = task.title,
            description = task.description
        )
    }

    fun addTask(task: Task) {
        uiState(uiStates = uiState.value.copy(loading = true))
        viewModelScope.launch {
            TaskManager.addTask(task)
            loadTasks()
        }

    }

    fun loadTasks() {
        viewModelScope.launch {
            _tasks.value = TaskManager.getTasks()
        }
    }

    fun markTaskCompleted(taskId: String) {
        viewModelScope.launch {
            TaskManager.markTaskCompleted(taskId)
            loadTasks()
        }
    }

}