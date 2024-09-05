package com.example.todoapp.ui.adapters

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.data.entity.ToDo
import com.example.todoapp.databinding.CartviewBinding
import com.example.todoapp.ui.fragments.HomePageDirections
import com.example.todoapp.ui.viewmodels.HomaViewModel

class ToDoAdapter(var mcontext:Context,var toDoList:List<ToDo>,var viewModel: HomaViewModel) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {
    inner class ToDoViewHolder(val binding:CartviewBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
       val binding = CartviewBinding.inflate(LayoutInflater.from(mcontext),parent,false)
        return ToDoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return toDoList.size
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val list = toDoList[position]
        holder.binding.titleId.text = list.title
        holder.binding.textId.text = list.text

        holder.binding.toDoCartView.setOnClickListener {
            val hometodetails = HomePageDirections.actionHomePageToDetailsPage(list)
            Navigation.findNavController(it).navigate(hometodetails)
        }

        holder.binding.deleteImage .setOnClickListener {

            val message="${list.title} adlı kişiyi silmek istediğinize emin misiniz?"
            deleteDialog(it,message){
                //silme işlemi yeri
                viewModel.delete(list.id)

            }
        }
    }


    fun deleteDialog(view: View, message:String, onDeleteConfirmed:() -> Unit){
        val builder= AlertDialog.Builder(view.context)
        builder.setTitle("Silme İşlemi")
        builder.setMessage(message)
        builder.setPositiveButton("Evet"){dialog,_ ->
            onDeleteConfirmed()
            dialog.dismiss()
        }
        builder.setNegativeButton("Hayır"){dialog,_ ->
            dialog.dismiss()
        }
        val dialog:AlertDialog=builder.create()
        dialog.show()
    }
}