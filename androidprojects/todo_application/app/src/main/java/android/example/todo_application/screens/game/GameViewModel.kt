package android.example.todo_application.screens.game

import android.app.Application
import android.example.todo_application.database.Note
import android.example.todo_application.database.NoteDatabase
import android.example.todo_application.database.NoteDatabaseDao
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class GameViewModel(
    private val dataSource: NoteDatabaseDao,
    private val application: Application
): AndroidViewModel(application) {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(viewModelJob+Dispatchers.Main)

    val notes = MutableLiveData<List<Note>>()

    init {
        initializeNotes()
    }

    private fun initializeNotes() {
        uiScope.launch {
            notes.value = dataSource.getAllNotes()
        }
    }

    private val _navigateToNoteDetail = MutableLiveData<Long>()
    val navigateToNoteDetail
        get() = _navigateToNoteDetail

    private val _onDeleteEvent = MutableLiveData<Boolean>()
    val onDeleteEvent
        get() = _onDeleteEvent

    fun onDeleteClicked(noteID: Long) {
        uiScope.launch {
            dataSource.delete(noteID ?: 0)
            notes.value = dataSource.getAllNotes()
        }
    }

    fun onAddClicked() {
        _navigateToNoteDetail.value = -1
    }

    fun onNoteClicked(id: Long) {
        _navigateToNoteDetail.value = id
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}