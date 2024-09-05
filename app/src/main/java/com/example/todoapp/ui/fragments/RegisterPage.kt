package com.example.todoapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentRegisterPageBinding
import com.example.todoapp.ui.viewmodels.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterPage : Fragment() {

    private lateinit var binding: FragmentRegisterPageBinding
    private lateinit var viewModel: RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel: RegisterViewModel by viewModels()
        viewModel = tempViewModel
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterPageBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        binding.saveButton.setOnClickListener {
            val title = binding.titleRegister.text.toString().trim()
            val text = binding.textRegister.text.toString().trim()

            if (title.isEmpty() || text.isEmpty()) {
                Toast.makeText(requireContext(), "Boş alanları doldurunuz", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else{
                viewModel.add(title, text)
                Toast.makeText(requireContext(), "Kayıt Başarılı", Toast.LENGTH_SHORT).show()
            }

        }
        return binding.root
    }

}