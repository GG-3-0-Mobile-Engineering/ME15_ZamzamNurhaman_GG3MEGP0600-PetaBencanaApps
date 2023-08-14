package com.example.petabencana.presentation.ui.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.petabencana.data.datasource.local.ThemePreferences
import com.example.petabencana.databinding.FragmentSettingBinding
import com.example.petabencana.utils.helper.ThemeHelper

class SettingFragment : Fragment() {


    private val viewModel: SettingViewModel by viewModels<SettingViewModel>{
        SettingViewModel.factory(ThemePreferences(requireActivity()))
    }
    private lateinit var _binding: FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingBinding.inflate(inflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.appbarSetting.setupWithNavController(findNavController())

        _binding.switchMaterial.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setDarkTheme(isChecked)
        }

        viewModel.isDarkTheme().observe(viewLifecycleOwner) { isDarkMode ->
            _binding.switchMaterial.isChecked = isDarkMode
            when(isDarkMode){
                true-> ThemeHelper.enableDarkTheme()
                else-> ThemeHelper.disableDarkTheme()
            }
        }
    }


}