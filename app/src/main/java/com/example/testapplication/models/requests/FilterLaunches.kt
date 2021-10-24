package com.example.testapplication.models.requests

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilterLaunches (
    val year: Int,
    val order: String,
    val success: Boolean
): Parcelable
{
}