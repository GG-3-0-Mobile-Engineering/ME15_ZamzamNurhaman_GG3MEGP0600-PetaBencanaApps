package com.example.petabencana.presentation.ui.setting

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.petabencana.R
import com.example.petabencana.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {


    private lateinit var viewModel: SettingViewModel
    private lateinit var _binding: FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingBinding.inflate(inflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        _binding.appbarSetting.setNavigationOnClickListener {
            _binding.appbarSetting.findNavController().navigateUp()
        }
    }





}