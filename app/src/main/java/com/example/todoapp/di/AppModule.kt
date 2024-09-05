package com.example.todoapp.di

import android.content.Context
import androidx.room.Room
import com.example.todoapp.data.datasource.DataSource
import com.example.todoapp.data.repository.Repository
import com.example.todoapp.room.AppDatabase
import com.example.todoapp.room.ToDoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideTodoDao(@ApplicationContext context: Context): ToDoDao {
        val db= Room.databaseBuilder(context, AppDatabase::class.java,"todo.sqlite")
            .createFromAsset("todo.sqlite").build()
        return db.getToDoDao()
    }

    @Provides
    @Singleton
    fun provideToDoDataSource(todoDao: ToDoDao): DataSource {
        return DataSource(todoDao)
    }

    @Provides
    @Singleton
    fun provideToDoRepository(ds: DataSource): Repository {
        return Repository(ds)
    }


}