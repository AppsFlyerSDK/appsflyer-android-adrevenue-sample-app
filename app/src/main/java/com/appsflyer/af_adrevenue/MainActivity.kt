package com.appsflyer.af_adrevenue

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.appsflyer.af_adrevenue.databinding.ActivityMainBinding
import com.ironsource.mediationsdk.IronSource




class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
    }

    override fun onResume() {
        super.onResume()
        IronSource.onResume(this)
    }

    override fun onPause() {
        super.onPause()
        IronSource.onPause(this)
    }
}