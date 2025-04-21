package br.edu.satc.todolistcompose.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
fun InvalidAlertDialog(
    showDialog: MutableState<Boolean>,
){
    AlertDialog(
        onDismissRequest = { showDialog.value = false },
        title = { Text("Campos Inválidos") },
        text = { Text("Por favor, preencha todos os campos.") },
        confirmButton = {
            Button(onClick = {
                showDialog.value = false
            }) {
                Text("Ok")
            }
        }
    )
}