package com.example.todoapp.data.repository

import com.example.todoapp.data.datasource.DataSource
import com.example.todoapp.data.entity.ToDo
import javax.inject.Inject

class Repository @Inject constructor(var ds: DataSource) {

    suspend fun infoList(): List<ToDo> =ds.infoList()
    suspend fun search(searchWord:String)=ds.search(searchWord)
    suspend fun delete(id: Int)=ds.delete(id)
    suspend fun update(id: Int,title:String,text:String)=ds.update(id,title,text)
    suspend fun add(title:String,text:String)=ds.add(title,text)

}