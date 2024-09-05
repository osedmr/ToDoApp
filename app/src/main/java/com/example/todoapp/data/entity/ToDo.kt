package com.example.todoapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "todo")
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo (name="id") @NotNull val id:Int,
    @ColumnInfo (name="title") @NotNull val title:String,
    @ColumnInfo (name="text") @NotNull val text:String): Serializable
