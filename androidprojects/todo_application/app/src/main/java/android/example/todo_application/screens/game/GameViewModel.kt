package android.example.todo_application.screens.game

import android.app.Application
import android.example.todo_application.database.Note
import android.example.todo_application.database.NoteDatabaseDao
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
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

    private val _onNoteClickedEvent = MutableLiveData<Boolean>()
    val onNoteClickedEvent: LiveData<Boolean>
        get() = _onNoteClickedEvent

    private val _noteIdMonitor = MutableLiveData<Long>()
    val noteIdMonitor
        get() = _noteIdMonitor

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
        _noteIdMonitor.value = -1
    }

    fun onNoteClicked(id: Long) {
        _noteIdMonitor.value = id
        _onNoteClickedEvent.value = true
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun onDoneNavigating() {
        _noteIdMonitor.value = -2L
        _onNoteClickedEvent.value = false
    }
}