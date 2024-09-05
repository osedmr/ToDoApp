package com.example.todoapp.data.datasource

import com.example.todoapp.data.entity.ToDo
import com.example.todoapp.room.ToDoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DataSource @Inject constructor(var todoDao: ToDoDao) {

    suspend fun infoList(): List<ToDo> =
        withContext(Dispatchers.IO){

            return@withContext todoDao.todoList()
        }

    suspend fun search(searchWord:String): List<ToDo> =
        withContext(Dispatchers.IO){
            return@withContext todoDao.searchByName(searchWord)
        }

    suspend fun delete(id: Int){
        val delete = ToDo(id,"","")
        todoDao.delete(delete)
    }

    suspend fun update(id: Int,title:String,text:String) {
        val update = ToDo(id,title,text)
        todoDao.update(update)

    }

    suspend fun add(title:String,text:String){
        val newToDo = ToDo(0,title,text)
        todoDao.add(newToDo)

    }

}