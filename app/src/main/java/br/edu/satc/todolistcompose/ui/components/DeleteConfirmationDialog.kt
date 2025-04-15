package br.edu.satc.todolistcompose.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import br.edu.satc.todolistcompose.TaskData
import br.edu.satc.todolistcompose.ui.ViewModel.TaskViewModel

@Composable
fun DeleteConfirmationDialog(
    showDialog: MutableState<Boolean>,
    task: TaskData,
    onConfirm: (TaskData) -> Unit,
    onCancel: () -> Unit,
    viewModel: TaskViewModel
){
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text("Confirmar Exclusão") },
            text = { Text("Deseja realmente apagar a Task?") },
            confirmButton = {
                Button(onClick = {
                    onConfirm(task)
                    showDialog.value = false
                }) {
                    Text("Sim")
                }
            },
            dismissButton = {
                Button(onClick = {
                    onCancel()
                    showDialog.value = false
                }) {
                    Text("Não")
                }
            }
        )
    }
}