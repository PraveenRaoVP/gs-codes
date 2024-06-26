package android.caged.jogfit.presentation.settings

import android.caged.jogfit.presentation.components.ActionBar
import android.caged.jogfit.presentation.navigation.Routes
import android.caged.jogfit.utils.ThemeManager
import android.caged.jogfit.utils.ThemePreferenceManager
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(navigatePopUp: (String, String) -> Unit, previousScreen: String) {

    val themeOptions = listOf("Light Mode", "Dark Mode")
    var selectedTheme by remember { mutableStateOf(themeOptions[0]) }

    var isExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            ActionBar(title = "Settings", isBackEnabled = true, onBackClick = {
                navigatePopUp(
                    previousScreen,
                    Routes.SettingsScreen.route
                )
            })
        }
    ) { innerPadding ->
        val topPadding = innerPadding.calculateTopPadding()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = topPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(color = MaterialTheme.colorScheme.background)
                        .align(Alignment.Start)
                ) {
                    IconButton(onClick = {
                        navigatePopUp(
                            previousScreen,
                            Routes.SettingsScreen.route
                        )
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                // dropdown containing light mode or dark mode
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, end = 30.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Theme")
                    Box(
                        modifier = Modifier
                            .background(color = MaterialTheme.colorScheme.surfaceVariant)
                            .clip(RoundedCornerShape(20.dp))
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                shape = RoundedCornerShape(20.dp)
                            ),
                        // rounded corners
                    ) {
                        TextButton(onClick = { isExpanded = true }) {
                            Text(selectedTheme)
                            Image(
                                imageVector =
                                    if (!isExpanded)
                                        Icons.Default.KeyboardArrowDown
                                    else
                                        Icons.Default.KeyboardArrowUp,
                                contentDescription = null
                            )
                        }
                        DropdownMenu(
                            expanded = isExpanded,
                            onDismissRequest = { isExpanded = false },
                        ) {
                            themeOptions.forEach { theme ->
                                DropdownMenuItem(onClick = {
                                    selectedTheme = theme
                                    isExpanded = false
                                    ThemePreferenceManager.setDarkTheme(theme == "Dark Mode")
                                }, text = { Text(theme) })
                            }
                        }
                    }
                }
            }
        }
    }
}