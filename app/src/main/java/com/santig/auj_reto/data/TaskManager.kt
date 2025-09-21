package com.santig.auj_reto.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.santig.auj_reto.domain.Task
import java.util.UUID

object TaskManager {

    private const val PREFS_NAME = "task_prefs"
    private const val TASKS_KEY = "tasks"

    private lateinit var sharedPreferences: SharedPreferences
    private val gson = Gson()

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun getTasks(): MutableList<Task> {
        val json = sharedPreferences.getString(TASKS_KEY, null) ?: return mutableListOf()
        val type = object : TypeToken<MutableList<Task>>() {}.type
        return gson.fromJson(json, type)
    }

    private fun saveTasks(taskList: List<Task>) {
        val json = gson.toJson(taskList)
        sharedPreferences.edit().putString(TASKS_KEY, json).apply()
    }

    fun addTask(task: Task) {
        val taskList = getTasks()
        val newTask = Task(
            id = UUID.randomUUID().toString(),
            title = task.title,
            description = task.description,
            completed = false
        )
        taskList.add(newTask)
        saveTasks(taskList)
    }

    fun markTaskCompleted(taskId: String) {
        val taskList = getTasks()
        val index = taskList.indexOfFirst { it.id == taskId }
        if (index != -1 && !taskList[index].completed) {
            taskList[index] = taskList[index].copy(completed = true)
            saveTasks(taskList)
        }
    }

}