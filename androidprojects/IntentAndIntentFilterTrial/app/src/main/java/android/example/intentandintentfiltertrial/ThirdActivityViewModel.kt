package android.example.intentandintentfiltertrial

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ThirdActivityViewModel : ViewModel() {
    // when i change the orientation, i want the text to be retained
    var contents by mutableStateOf("")
        private set

    fun onContentsChange(newContents: String) {
        contents = newContents
    }
}