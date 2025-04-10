package br.edu.satc.todolistcompose.ui.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.satc.todolistcompose.TaskDao
import br.edu.satc.todolistcompose.TaskData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskViewModel(private val taskDao: TaskDao) : ViewModel() {

    private val _tasks = MutableStateFlow<List<TaskData>>(emptyList())
    val tasks: StateFlow<List<TaskData>> = _tasks

    init {
        loadTasks()
    }

    fun loadTasks() {
        viewModelScope.launch {
            _tasks.value = taskDao.getAll()
        }
    }

    fun addTask(title: String, description: String) {
        viewModelScope.launch {
            taskDao.insertAll(TaskData(0, title, description, false))
            loadTasks()
        }
    }

    fun updateTask(task: TaskData) {
        viewModelScope.launch {
            taskDao.updateAll(task)
            loadTasks()
        }
    }

    fun deleteTask(task: TaskData) {
        viewModelScope.launch {
            taskDao.delete(task)
            loadTasks()
        }
    }
}