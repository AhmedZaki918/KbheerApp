package com.ahmed.khabertask.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ahmed.khabertask.R
import com.ahmed.khabertask.data.local.Constants.TOKEN_KEY
import com.ahmed.khabertask.data.model.LoginRequest
import com.ahmed.khabertask.data.model.LoginResponse
import com.ahmed.khabertask.data.network.Resource
import com.ahmed.khabertask.databinding.ActivityLoginBinding
import com.ahmed.khabertask.ui.details.DetailsActivity
import com.ahmed.khabertask.util.handleApiError
import com.ahmed.khabertask.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: MainViewModel
    private var mobileNumber = ""
    private var password = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.loginResponse.observe(this) { response ->
            updateUi(response)
        }
        binding.btnLogin.setOnClickListener(this)
    }


    override fun onClick(item: View) {
        if (isUserInputEmpty()) {
            login()
        } else {
            toast(getString(R.string.missing_values))
        }
    }


    private fun login() {
        switchVisibility(true)
        val userData = LoginRequest(mobileNumber, password.toInt())
        viewModel.login(userData)
    }


    private fun isUserInputEmpty(): Boolean {
        binding.apply {
            mobileNumber = etMobileNumber.text.toString().trim()
            password = etPassword.text.toString().trim()
            return mobileNumber.isNotEmpty() || password.isNotEmpty()
        }
    }


    private fun updateUi(response: Resource<LoginResponse>?) {
        switchVisibility(false)
        if (response is Resource.Success) {
            viewModel.saveToken(TOKEN_KEY, response.value.Token!!)
            toast(response.value.ArabicMessage)
            startActivity(Intent(this, DetailsActivity::class.java))
        } else {
            handleApiError(response as Resource.Failure)
        }
    }


    private fun switchVisibility(operation: Boolean) {
        binding.apply {
            if (operation) {
                progressBar.visibility = VISIBLE
                btnLogin.visibility = INVISIBLE
            } else {
                progressBar.visibility = INVISIBLE
                btnLogin.visibility = VISIBLE
            }
        }
    }
}