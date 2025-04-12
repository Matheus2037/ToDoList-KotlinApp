package br.edu.satc.todolistcompose

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update

@Entity(tableName = "task")
data class TaskData (
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "task_title") val title: String,
    @ColumnInfo(name = "task_description") val description: String,
    @ColumnInfo(name = "task_complete") val complete: Boolean
)

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAll(): List<TaskData>

    @Query("SELECT * FROM task WHERE uid IN (:taskIds)")
    fun loadAllByIds(taskIds: IntArray): List<TaskData>

    @Query("SELECT * FROM task WHERE task_title LIKE :first")
    fun findByTitle(first: String): TaskData

    @Query("SELECT * FROM task WHERE task_complete = true")
    fun findByComplete(): List<TaskData>

    @Update
    fun updateAll(vararg tasks: TaskData)

    @Insert
    fun insertAll(vararg tasks: TaskData)

    @Delete
    fun delete(task: TaskData)

    @Query("DELETE FROM task")
    fun deleteAll()
}



@Database(entities = arrayOf(TaskData::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}