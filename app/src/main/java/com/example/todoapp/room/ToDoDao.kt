package com.example.todoapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.data.entity.ToDo

@Dao
interface ToDoDao {

    @Query("SELECT * FROM todo")
    suspend fun todoList():List<ToDo>

    @Query("SELECT * FROM todo WHERE title LIKE '%' || :name || '%'")
    suspend fun searchByName(name: String): List<ToDo>

    @Delete
    suspend fun delete(todo: ToDo)
    @Insert
    suspend fun add(todo: ToDo)
    @Update
    suspend fun update(todo: ToDo)

}