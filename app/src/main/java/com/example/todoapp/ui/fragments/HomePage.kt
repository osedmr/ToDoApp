package com.example.todoapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.R
import com.example.todoapp.data.entity.ToDo
import com.example.todoapp.databinding.FragmentHomePageBinding
import com.example.todoapp.ui.adapters.ToDoAdapter
import com.example.todoapp.ui.viewmodels.HomaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePage : Fragment() {
    private lateinit var binding: FragmentHomePageBinding
    private lateinit var viewModel: HomaViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding=FragmentHomePageBinding.inflate(inflater,container,false)


        viewModel.toDoList.observe(viewLifecycleOwner){
            val adapter = ToDoAdapter(requireContext(),it.toMutableList(),viewModel)
            binding.toDoRv.layoutManager= LinearLayoutManager(requireContext())
            binding.toDoRv.adapter = adapter
        }


        binding.fabButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homePage_to_registerPage)
        }


        binding.searchViewId.setOnQueryTextListener(object :SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    viewModel.search(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if (newText != null) {
                    viewModel.search(newText)
                }
                return true
            }
        })




        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:HomaViewModel by viewModels()
        viewModel=tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.infoList()

    }

}