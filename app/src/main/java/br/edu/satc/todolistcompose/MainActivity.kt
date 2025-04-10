package br.edu.satc.todolistcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import br.edu.satc.todolistcompose.ui.ViewModel.TaskViewModel
import br.edu.satc.todolistcompose.ui.ViewModel.TaskViewModelFactory
import br.edu.satc.todolistcompose.ui.screens.HomeScreen
import br.edu.satc.todolistcompose.ui.theme.ToDoListComposeTheme
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "satc-demo-room"
        ).allowMainThreadQueries().build()

        val taskDao = db.taskDao()

        if (taskDao.getAll().isEmpty()) {
            val task1 = TaskData(0, "Estudar Kotlin", "Revisar funções e listas", false)
            val task2 = TaskData(0, "Fazer projeto Room", "Implementar DAO e mostrar no app", false)

            taskDao.insertAll(task1, task2)
        }



        setContent {

            val viewModel: TaskViewModel = viewModel(
                factory = TaskViewModelFactory(taskDao)
            )

            ToDoListComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(taskViewModel = viewModel)
                }
            }
        }
    }
}
