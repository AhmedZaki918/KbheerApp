package com.ahmed.khabertask.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ahmed.khabertask.R
import com.ahmed.khabertask.databinding.ActivityDetailsBinding
import com.ahmed.khabertask.util.SetupChart
import com.github.mikephil.charting.charts.PieChart

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var pieChart: PieChart
    private lateinit var setupChart: SetupChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pieChart = findViewById(R.id.chart)
        setupChart = SetupChart(pieChart,this)
        setupChart.apply {
            setupPieChart()
            loadPieChartData()
        }
    }
}