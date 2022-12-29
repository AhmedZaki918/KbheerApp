package com.ahmed.khabertask.util

import android.content.Context
import android.graphics.Color
import com.ahmed.khabertask.R
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter


class SetupChart(
    private val pieChart: PieChart,
    context: Context,
    private val deduction: Float,
    private val allowances: Float
) {

    private var customColors =
        context.resources.getIntArray(R.array.salary_colors)

    fun setupPieChart() {
        pieChart.apply {
            isDrawHoleEnabled = false
            setUsePercentValues(true)
            description.isEnabled = false
            legend.isEnabled = false
        }
    }

    fun loadPieChartData() {
        val entries: ArrayList<PieEntry> = ArrayList()
        entries.add(PieEntry(allowances, ""))
        entries.add(PieEntry(deduction, ""))
        val colors: ArrayList<Int> = ArrayList()

        for (color in customColors) {
            colors.add(color)
        }
        val dataSet = PieDataSet(entries, "")
        dataSet.colors = colors

        PieData(dataSet).apply {
            setDrawValues(true)
            setValueFormatter(PercentFormatter(pieChart))
            setValueTextSize(14f)
            setValueTextColor(Color.WHITE)
            pieChart.data = this
        }
        pieChart.invalidate()
        pieChart.animateY(1400, Easing.EaseInOutQuad)
    }
}