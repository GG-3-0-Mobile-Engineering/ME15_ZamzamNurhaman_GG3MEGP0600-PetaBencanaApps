package com.example.petabencana

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.petabencana.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navigation_fragment) as NavHostFragment
        navController = navHostFragment.navController
//        WindowCompat.setDecorFitsSystemWindows(window, false)

    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.navigation_fragment).navigateUp()
                || super.onSupportNavigateUp()
    }
}