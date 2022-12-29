package com.ahmed.khabertask.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ahmed.khabertask.R
import com.ahmed.khabertask.ui.details.DetailsActivity
import com.ahmed.khabertask.ui.home.LoginActivity
import com.ahmed.khabertask.ui.home.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        hasPermissionToLogin()
    }

    private fun hasPermissionToLogin(){
        viewModel.token.observe(this){token ->
            if (token != ""){
                startActivity(Intent(this, DetailsActivity::class.java))
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }
    }
}