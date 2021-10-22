package com.example.testapplication.models

data class CompanyInfo(
    val name: String,
    val founder: String,
    val founded: String,
    val employees: Int,
    val launch_sites: Short,
    val valuation: Long
) {
}