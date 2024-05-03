package android.example.todo_application.screens.game

import android.app.Application
import android.example.todo_application.database.Note
import android.example.todo_application.database.NoteDatabase
import android.example.todo_application.database.NoteDatabaseDao
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class GameViewModel(
    private val dataSource: NoteDatabaseDao,
    private val application: Application
): AndroidViewModel(application) {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(viewModelJob+Dispatchers.Main)

    private val notes = dataSource.getAllNotes()

    private val _navigateToNoteDetail = MutableLiveData<Long>()
    val navigateToNoteDetail
        get() = _navigateToNoteDetail

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