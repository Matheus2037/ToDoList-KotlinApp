package br.edu.satc.todolistcompose.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SettingsButtonWithMenu(
    onFilterChange: (Boolean) -> Unit,
    onDarkModeToggle: (Boolean) -> Unit,
    filterByCompleteHome: Boolean
) {
    var expanded by remember { mutableStateOf(false) }
    var isDarkMode by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.wrapContentSize(Alignment.TopEnd)
    ) {
        IconButton(onClick = { expanded = true }) {
            Icon(Icons.Rounded.Settings, contentDescription = "Configurações")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text(if (filterByCompleteHome) "Em andamento" else "Mostrar Completas") },
                onClick = {
                    onFilterChange(!filterByCompleteHome)
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text(if (isDarkMode) "Desativar Dark Mode" else "Ativar Dark Mode") },
                onClick = {
                    isDarkMode = !isDarkMode
                    onDarkModeToggle(isDarkMode)
                    expanded = false
                }
            )
        }
    }
}