package nl.hva.fdmci.mad.mad_individual_assignment.ui.task.create

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import nl.hva.fdmci.mad.mad_individual_assignment.database.Task
import nl.hva.fdmci.mad.mad_individual_assignment.database.TaskRepository

class CreateTaskViewModel (application: Application) : AndroidViewModel(application) {
    private val taskRepository = TaskRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    var task = MutableLiveData<Task?>()

    val error = MutableLiveData<String?>()
    val success = MutableLiveData<Boolean?>()

    fun updateTask() {
        if (isTaskValid()) {
            mainScope.launch {
                withContext(Dispatchers.IO) {
                    taskRepository.updateTask(task.value!!)
                }
                success.value = true
            }
        }
    }

    fun insertTask() {
        if (isTaskValid()) {
            mainScope.launch {
                withContext(Dispatchers.IO) {
                    taskRepository.insertTask(task.value!!)
                }
                success.value = true
            }
        }
    }

    fun deleteAllTasks() {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                taskRepository.deleteAllTasks()

            }
        }
    }

    private fun isTaskValid(): Boolean {

            return when {
                task.value!!.title.isBlank() -> {
                    error.value = "Title must not be empty"
                    false
                }
                task.value!!.description.isBlank() -> {
                    error.value = "Description must not be empty"
                    false
                }
                else -> true
            }

    }
}