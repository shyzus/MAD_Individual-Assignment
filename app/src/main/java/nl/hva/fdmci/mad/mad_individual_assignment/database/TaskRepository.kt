package nl.hva.fdmci.mad.mad_individual_assignment.database

import android.content.Context
import androidx.lifecycle.LiveData

class TaskRepository(context: Context) {
    
    private val taskDao: TaskDao

    init {
        val database = TaskRoomDatabase.getDatabase(context)
        taskDao = database!!.taskDao()
    }

    fun getAllTasks(): LiveData<List<Task>> {
        return taskDao.getAllTasks()
    }

    fun deleteAllTasks() {
        taskDao.deleteAllTasks()
    }

    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

    suspend fun insertTask(task: Task) {
        taskDao.insertTask(task)
    }

    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }
    
}