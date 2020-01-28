package nl.hva.fdmci.mad.mad_individual_assignment.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = [Task::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class TaskRoomDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        private const val DATABASE_NAME = "TASK_DATABASE"

        @Volatile
        private var INSTANCE: TaskRoomDatabase? = null

        fun getDatabase(context: Context): TaskRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(TaskRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            TaskRoomDatabase::class.java, DATABASE_NAME
                        )
                            .fallbackToDestructiveMigration()
                            .addCallback(object : RoomDatabase.Callback() {
                                override fun onCreate(db: SupportSQLiteDatabase) {
                                    super.onCreate(db)
                                    INSTANCE?.let { database ->
                                        CoroutineScope(Dispatchers.IO).launch {
                                            database.taskDao().insertTask(
                                                Task(
                                                    "Title","Description"
                                                )
                                            )
                                        }
                                    }
                                }
                            })
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}