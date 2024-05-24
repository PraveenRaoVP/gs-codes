package android.example.workmanagertrial

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.util.UUID

class PhotoViewModel : ViewModel() {
    var uncompressedUri: Uri? by mutableStateOf(null)
        private set

    // what is private set?  It is a way to make a property read-only from outside the class.
    // How is it different from val?  A val is read-only from inside the class as well,
    // while a property with a private set can be modified from inside the class.

    var compressedBitmap: Bitmap? by mutableStateOf(null)
        private set

    var workId: UUID? by mutableStateOf(null)
        private set

    fun updateUnCompressedUri(uri: Uri?) {
        uncompressedUri = uri
    }

    fun updateUncCompressedBitmap(bitmap: Bitmap?) {
        compressedBitmap = bitmap
    }

    fun updateWorkId(id: UUID?) {
        workId = id
    }

}