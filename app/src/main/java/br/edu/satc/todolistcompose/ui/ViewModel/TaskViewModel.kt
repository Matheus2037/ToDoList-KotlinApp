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

    fun loadTasks(filterByComplete: Boolean = false) {
        viewModelScope.launch {
            if(filterByComplete == false) {
                _tasks.value = taskDao.getAll()
            }else{
                _tasks.value = taskDao.findByComplete()
            }
        }
    }

    fun addTask(title: String, description: String) {
        viewModelScope.launch {
            taskDao.insertAll(TaskData(0, title, description, false))
            loadTasks(filterByComplete = false)
        }
    }

    fun updateTask(task: TaskData) {
        viewModelScope.launch {
            taskDao.updateAll(task)
            loadTasks(filterByComplete = false)
        }
    }

    fun deleteTask(task: TaskData) {
        viewModelScope.launch {
            taskDao.delete(task)
            loadTasks(filterByComplete = false)
        }
    }
}