package com.example.todoapp.ui.viewmodels


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapp.data.entity.ToDo
import com.example.todoapp.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomaViewModel @Inject constructor(var repo:Repository) : ViewModel() {

    val toDoList = MutableLiveData<List<ToDo>>()

    init {
        infoList()
    }

    fun infoList(){
        CoroutineScope(Dispatchers.Main).launch {
            toDoList.value = repo.infoList()
        }
    }

    fun search(searchWord:String){
        CoroutineScope(Dispatchers.Main).launch {
            toDoList.value = repo.search(searchWord)
        }
    }

    fun delete(id: Int){
        CoroutineScope(Dispatchers.Main).launch {
            repo.delete(id)
            infoList()
        }

    }
}