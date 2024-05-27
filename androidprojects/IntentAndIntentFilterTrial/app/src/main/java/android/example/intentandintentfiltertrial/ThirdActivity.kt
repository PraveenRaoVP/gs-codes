package android.example.intentandintentfiltertrial

import android.content.Intent
import android.example.intentandintentfiltertrial.ui.theme.IntentAndIntentFilterTrialTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

class ThirdActivity : ComponentActivity() {
    private val thirdActivityViewModel by viewModels<ThirdActivityViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
//            var contents by remember {
//                mutableStateOf("")
//            }
            IntentAndIntentFilterTrialTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    OutlinedTextField(
                        value = thirdActivityViewModel.contents,
                        onValueChange = { thirdActivityViewModel.onContentsChange(it) },
                        label = {
                            Text("Enter text to share", color = Color.Black)
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Blue,
                            unfocusedBorderColor = Color.Gray
                        ),
                        textStyle = TextStyle(color = Color.Black),
                        modifier = Modifier.padding(16.dp)
                    )
                    Button(
                        onClick = {
                            val intent = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(Intent.EXTRA_TEXT, thirdActivityViewModel.contents)
                            }

                            if (intent.resolveActivity(packageManager) != null) {
                                startActivity(intent)
                            }
                        },
                        enabled = thirdActivityViewModel.contents.isNotEmpty()
                    ) {
                        Text(text = "Share")
                    }
                }
            }
        }
    }
}