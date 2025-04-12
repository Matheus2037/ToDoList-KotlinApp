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
    private var currentFilterByComplete = false

    init {
        loadTasks()
    }

    fun loadTasks(filterByComplete: Boolean = false) {
        currentFilterByComplete = filterByComplete
        viewModelScope.launch {
            _tasks.value = if (filterByComplete) {
                taskDao.findByComplete()
            } else {
                taskDao.getAll()
            }
        }
    }

    fun addTask(title: String, description: String) {
        viewModelScope.launch {
            taskDao.insertAll(TaskData(0, title, description, false))
            loadTasks(currentFilterByComplete)
        }
    }

    fun updateTask(task: TaskData) {
        viewModelScope.launch {
            taskDao.updateAll(task)
            loadTasks(currentFilterByComplete)
        }
    }

    fun deleteTask(task: TaskData) {
        viewModelScope.launch {
            taskDao.delete(task)
            loadTasks(currentFilterByComplete)
        }
    }

    fun deleteAllTasks() {
        viewModelScope.launch {
            taskDao.deleteAll()
            loadTasks(currentFilterByComplete)
        }
    }
}