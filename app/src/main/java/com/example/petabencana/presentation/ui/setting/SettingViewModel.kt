package com.example.petabencana.presentation.ui.setting


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.petabencana.data.datasource.local.Preferences
import kotlinx.coroutines.launch

class SettingViewModel(private val preference: Preferences) : ViewModel() {

    class factory(private  val preference: Preferences): ViewModelProvider.NewInstanceFactory(){
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SettingViewModel(preference) as T
        }
    }

    fun isDarkTheme() = preference.getIsDarkMode().asLiveData()
    fun setDarkTheme(value: Boolean){
        viewModelScope.launch {
            preference.setIsDarkMode(value)
        }
    }


}