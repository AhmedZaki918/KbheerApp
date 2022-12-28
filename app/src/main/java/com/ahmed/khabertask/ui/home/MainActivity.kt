package com.ahmed.khabertask.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ahmed.khabertask.R
import com.ahmed.khabertask.databinding.ActivityDetailsBinding
import com.ahmed.khabertask.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}