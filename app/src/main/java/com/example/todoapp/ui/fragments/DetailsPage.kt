package com.example.todoapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentDetailsPageBinding
import com.example.todoapp.ui.viewmodels.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsPage : Fragment() {

   private lateinit var binding: FragmentDetailsPageBinding
    private lateinit var viewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: DetailsViewModel by viewModels()
        viewModel = tempViewModel

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailsPageBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        val bundle:DetailsPageArgs by navArgs()
        val info = bundle.info
        binding.titleRegister.setText(info.title)
        binding.textRegister.setText(info.text)


        binding.saveButton.setOnClickListener {
            val title = binding.titleRegister.text.toString().trim()
            val text = binding.textRegister.text.toString().trim()
            viewModel.update(info.id,title,text)
            Toast.makeText(requireContext(), "Güncelleme Başarılı", Toast.LENGTH_SHORT).show()
        }


        return binding.root
    }

}