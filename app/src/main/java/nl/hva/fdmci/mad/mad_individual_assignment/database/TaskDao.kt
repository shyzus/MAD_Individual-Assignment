package nl.hva.fdmci.mad.mad_individual_assignment.database

import androidx.lifecycle.LiveData
import androidx.room.*
import nl.hva.fdmci.mad.mad_individual_assignment.database.Task

@Dao
interface TaskDao {
    @Insert
    suspend fun insertTask(task: Task)

    @Query("SELECT * FROM Task WHERE id = :id LIMIT 1")
    fun getTaskById(id: Int): LiveData<Task?>

    @Query("SELECT * FROM Task")
    fun getAllTasks(): LiveData<List<Task>>

    @Query("DELETE FROM Task")
    fun deleteAllTasks()

    @Delete
    fun deleteTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)
}