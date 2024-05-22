package android.example.todo_app_compose

import android.util.Log
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class WellnessViewModel : ViewModel() {

    private val _list = getWellnessTasks().toMutableStateList()
    val list: List<WellnessTask>
        get() = _list


    fun remove(item: WellnessTask) {
        _list.remove(item)
        Log.i("WellnessViewModel", "Removing item: ${item.label}")
    }

    fun getWellnessTasks() = List(30) {
            i -> WellnessTask(i, "Task $i")
    }

    fun changeTaskChecked(task: WellnessTask, checked: Boolean) {
        _list.find { it.id == task.id}?.let {
            it.checked = checked
        }
    }
}