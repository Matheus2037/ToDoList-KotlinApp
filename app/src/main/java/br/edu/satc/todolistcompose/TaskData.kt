package br.edu.satc.todolistcompose

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update

@Entity
data class TaskData (
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "task_title") val title: String,
    @ColumnInfo(name = "task_description") val description: String,
    @ColumnInfo(name = "task_complete") val complete: Boolean
)

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAll(): List<Task>

    @Query("SELECT * FROM task WHERE uid IN (:taskIds)")
    fun loadAllByIds(userIds: IntArray): List<Task>

    @Query("SELECT * FROM task WHERE task_title LIKE :first")
    fun findByTitle(first: String, last: String): Task

    @Update
    fun updateAll(vararg users: Task)

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(task: Task)
}
