package android.example.todo_application.screens.notes

import android.app.Application
import android.example.todo_application.database.Note
import android.example.todo_application.database.NoteDatabaseDao
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class NoteViewModel(
    private val dataSource: NoteDatabaseDao,
    private val application: Application
): AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(viewModelJob+ Dispatchers.Main)
    private var _navigateToGame = MutableLiveData<Boolean>()

    val navigateToGame
        get() = _navigateToGame

    var noteId: Long = 0L
    var title = MutableLiveData<String>()
    var content = MutableLiveData<String>()

    init {
        if(noteId == -1L) {
            title.value = ""
            content.value = ""
        } else {
            uiScope.launch {
                val note = dataSource.get(noteId)
                title.value = note?.title
                content.value = note?.content
            }
        }
    }

    fun onClearClicked() {
        title.value = ""
        content.value = ""
    }

    private var _showErrorToast = MutableLiveData<Boolean>()
    val showErrorToast
        get() = _showErrorToast

    fun doneShowingErrorToast() {
        _showErrorToast.value = false
    }

    fun onSubmitClicked() {
        Log.i("NoteViewModel", "title: ${title.value} content: ${content.value}")
        if(title.value == null || title.value!!.isEmpty()) {
            _showErrorToast.value = true
            return
        }
        uiScope.launch {
            if(noteId.toInt() == -1) {
                dataSource.insert(Note(
                    title = title.value.toString()!!,
                    content = content.value?.toString() ?: ""
                ))
            } else {
                dataSource.update(Note(
                    noteId,
                    title = title.value.toString()!!,
                    content = content.value?.toString() ?: ""
                ))
            }
        }
        _navigateToGame.value = true
        Log.i("NoteViewModel", "onSubmitClicked")
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun doneNavigating() {
        _navigateToGame.value = false
    }

//    fun onDeleteClicked(noteID: Long) {
//        uiScope.launch {
//            dataSource.delete(noteID ?: 0)
//            notes.value = dataSource.getAllNotes()
//        }
//    }
}