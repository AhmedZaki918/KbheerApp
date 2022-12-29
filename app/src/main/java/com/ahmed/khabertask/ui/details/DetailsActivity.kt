package com.ahmed.khabertask.ui.details

import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ahmed.khabertask.R
import com.ahmed.khabertask.data.model.details.DetailsResponse
import com.ahmed.khabertask.data.network.Resource
import com.ahmed.khabertask.databinding.ActivityDetailsBinding
import com.ahmed.khabertask.util.SetupChart
import com.ahmed.khabertask.util.handleApiError
import com.github.mikephil.charting.charts.PieChart
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var viewModel: DetailsViewModel
    private var detailsResponse: DetailsResponse? = null

    private lateinit var pieChart: PieChart
    private lateinit var setupChart: SetupChart

    private var totalAllowances: Double = 0.0
    private var deduction: Int = 0

    private var allowancesPercentage: Float = 0f
    private var deductionPercentage: Float = 0f


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[DetailsViewModel::class.java]
        viewModel.userResponse.observe(this) { response ->
            updateUi(response)
        }

        pieChart = findViewById(R.id.chart)
    }

    private fun updateUi(response: Resource<DetailsResponse>?) {
        binding.progressBar.visibility = INVISIBLE
        binding.cvHeader.visibility = VISIBLE
        binding.cvFooter.visibility = VISIBLE

        if (response is Resource.Success) {
            binding.apply {
                detailsResponse = response.value
                tvName.text = detailsResponse?.Payroll?.Employee?.get(0)?.EMP_DATA_AR
                tvJobTitle.text = detailsResponse?.Payroll?.Employee?.get(0)?.JOBNAME_AR
                tvTotalSalaryTop.text = calculateTotalSalary().toString()
                tvWorth.text = totalAllowances.toString()
                tvDeductions.text = deduction.toString()
                tvTotalSalaryBottom.text = totalAllowances.minus(deduction).toString()
                convertAllowancesToPercentage()
                drawPieChart()
                tvMainSalary.text = detailsResponse?.Payroll?.Allowences?.get(0)?.SAL_VALUE.toString()
                tvWorkEnvironment.text = detailsResponse?.Payroll?.Allowences?.get(1)?.SAL_VALUE.toString()
                tvOtherDeductions.text = deduction.toString()
            }
        } else {
            handleApiError(response as Resource.Failure)
        }
    }


    private fun calculateTotalSalary(): Double {
        val allowances = detailsResponse?.Payroll?.Allowences
        deduction = detailsResponse?.Payroll?.Deduction?.get(0)?.SAL_VALUE!!.toInt()

        if (allowances != null) {
            for (value in allowances.indices) {
                totalAllowances = totalAllowances.plus(allowances[value].SAL_VALUE!!)
            }
        }
        return totalAllowances.minus(deduction)
    }


    private fun convertAllowancesToPercentage() {
        val total = totalAllowances.plus(deduction)
        deductionPercentage = deduction.div(total).toFloat()
        allowancesPercentage = 1 - deductionPercentage
    }


    private fun drawPieChart(){
        setupChart = SetupChart(
            pieChart,
            this,
            deductionPercentage,
            allowancesPercentage
        )
        setupChart.apply {
            setupPieChart()
            loadPieChartData()
        }
    }
}