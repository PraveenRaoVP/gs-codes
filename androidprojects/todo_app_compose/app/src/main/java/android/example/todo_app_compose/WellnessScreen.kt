package android.example.todo_app_compose.ui.theme

import android.example.todo_app_compose.WaterCounter
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun WellnessScreen(modifier: Modifier = Modifier) {
    WaterCounter(modifier = modifier)
}


@Preview(showBackground = true)
@Composable
fun WellnessScreenPreview() {
    Todo_app_composeTheme {
        WellnessScreen()
    }
}