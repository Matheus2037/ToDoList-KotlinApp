package br.edu.satc.todolistcompose.ui.components

import android.util.Log
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.satc.todolistcompose.TaskData
import br.edu.satc.todolistcompose.ui.ViewModel.TaskViewModel
import br.edu.satc.todolistcompose.ui.theme.ToDoListComposeTheme

@Composable
fun TaskCard(
    task: TaskData,
    onTaskCompleteChange: (TaskData) -> Unit, // Nova função lambda
    viewModel: TaskViewModel,
) {

    var taskComplete by remember { mutableStateOf(task.complete) }
    val showDeleteDialog = remember { mutableStateOf(false) }
    var showMenu by remember { mutableStateOf(false) }

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ), modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
            .height(100.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        showMenu = true
                    }
                )
            }

    ) {
        val selectedTask = remember { mutableStateOf<TaskData?>(null) }

        Box {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = task.title,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            fontFamily = FontFamily.Serif
                        )
                    )
                    Checkbox(checked = taskComplete,
                        onCheckedChange = { isChecked ->
                            taskComplete = isChecked
                            onTaskCompleteChange(task.copy(complete = isChecked))
                            Log.d("TaskDebug", "UID: ${task.uid}, complete: $isChecked")
                        }
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = task.description, fontSize = 12.sp
                    )

                    IconButton(onClick = { showDeleteDialog.value = true }) {
                        Icon(
                            Icons.Rounded.Delete,
                            contentDescription = "Deletar Task"
                        )
                    }
                }
                DeleteConfirmationDialog(
                    showDialog = showDeleteDialog,
                    task = task,
                    onConfirm = { taskToDelete ->
                        viewModel.deleteTask(taskToDelete)
                    },
                    onCancel = {

                    },
                    viewModel = viewModel
                )
            }
            if (showMenu) {
                Box(modifier = Modifier.wrapContentSize(Alignment.TopStart),) {
                    TaskOnLongPressMenu(
                        onDismiss = { showMenu = false },
                        task = task,
                        onCompleteClick = {
                            val newComplete = !task.complete
                            taskComplete = newComplete
                            onTaskCompleteChange(task.copy(complete = newComplete))
                            showMenu = false
                        },
                        onDeleteClick = {
                            showDeleteDialog.value = true
                            showMenu = false
                        }
                    )
                }
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun TaskCardPreview() {
//    ToDoListComposeTheme {
//        TaskCard()
//    }
//}