package br.edu.satc.todolistcompose.ui.components

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import br.edu.satc.todolistcompose.TaskData


@Composable
fun TaskOnLongPressMenu(
    onDismiss: () -> Unit,
    onCompleteClick: () -> Unit,
    onDeleteClick: () -> Unit,
    task: TaskData,
) {

    DropdownMenu(
        expanded = true,
        onDismissRequest = { onDismiss() }
    ) {
        DropdownMenuItem(
            text = { Text("Marcar como ${if (task.complete) "incompleta" else "completa"}") },
            onClick = {
                onCompleteClick()
            }
        )
        DropdownMenuItem(
            text = { Text("Excluir") },
            onClick = {
                onDeleteClick()
                onDismiss()
            }
        )
    }
}