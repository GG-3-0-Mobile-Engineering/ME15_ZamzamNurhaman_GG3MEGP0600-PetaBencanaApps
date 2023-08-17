package com.example.petabencana.presentation.ui.setting


import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.petabencana.data.datasource.local.ThemePreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor (private val preference: ThemePreferences) : ViewModel() {

    fun isDarkTheme() = preference.getIsDarkMode().asLiveData()
    fun setDarkTheme(value: Boolean){
        viewModelScope.launch {
            preference.setIsDarkMode(value)
        }
    }


}