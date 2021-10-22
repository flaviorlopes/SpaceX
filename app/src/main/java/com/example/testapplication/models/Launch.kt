package com.example.testapplication.models

data class Launch(
    val mission_name: String,
    val launch_date_unix: Long,
    val rocket: Rocket,
    val launch_success: Boolean,
    val links: Links
)
{
}