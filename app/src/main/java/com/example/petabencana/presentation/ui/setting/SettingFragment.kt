package com.example.petabencana.presentation.ui.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.petabencana.databinding.FragmentSettingBinding
import com.example.petabencana.utils.helper.ThemeHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : Fragment() {

    private val viewModel: SettingViewModel by viewModels()
    private var _binding: FragmentSettingBinding? = null
    private val binding: FragmentSettingBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.appbarSetting.setupWithNavController(findNavController())

        binding.switchMaterial.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setDarkTheme(isChecked)
        }

        viewModel.isDarkTheme().observe(viewLifecycleOwner) { isDarkMode ->
            binding.switchMaterial.isChecked = isDarkMode
            when(isDarkMode){
                true-> ThemeHelper.enableDarkTheme()
                else-> ThemeHelper.disableDarkTheme()
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}