package android.example.intentandintentfiltertrial

import android.content.ActivityNotFoundException
import android.content.Intent
import android.example.intentandintentfiltertrial.ui.theme.IntentAndIntentFilterTrialTheme
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.net.toUri
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            val coroutineScope = rememberCoroutineScope()

            var isRickRolled by remember {
                mutableStateOf(false)
            }

            IntentAndIntentFilterTrialTheme {
                Column(modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    if(!isRickRolled) {

                        Text("Click for cute cat videos")
                        Button(onClick = {
                            Intent(Intent.ACTION_MAIN).apply {
                                `package` = "com.google.android.youtube"
                                // to a particular youtube video?
                                data = Uri.parse("https://youtu.be/dQw4w9WgXcQ?si=U2OItJIqMFOCBxEo")
                                try {
                                    startActivity(this)
                                } catch (e: ActivityNotFoundException) {
                                    startActivity(Intent(Intent.ACTION_VIEW, "https://youtu.be/dQw4w9WgXcQ?si=U2OItJIqMFOCBxEo".toUri()))
                                }
                                coroutineScope.launch {
                                    delay(2000)
                                    isRickRolled = true
                                }
                            }
                        }) {
                            Text(text = "Go To Youtube")
                        }
                    }
                    else {
                        Text("Ha, You Got RickRolled!!!")
                    }
                }
            }
        }
    }
}