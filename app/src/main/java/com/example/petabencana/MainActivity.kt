package com.example.petabencana

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.petabencana.data.datasource.local.ThemePreferences
import com.example.petabencana.databinding.ActivityMainBinding
import com.example.petabencana.presentation.ui.setting.SettingViewModel
import com.example.petabencana.utils.helper.ThemeHelper


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val viewModel: SettingViewModel by viewModels<SettingViewModel> {
        SettingViewModel.factory(ThemePreferences(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navigation_fragment) as NavHostFragment
        navController = navHostFragment.navController
//        WindowCompat.setDecorFitsSystemWindows(window, false)

        viewModel.isDarkTheme().observe(this) { isDarkMode ->
            when (isDarkMode) {
                true -> ThemeHelper.enableDarkTheme()
                else -> ThemeHelper.disableDarkTheme()
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.navigation_fragment).navigateUp()
                || super.onSupportNavigateUp()
    }
}