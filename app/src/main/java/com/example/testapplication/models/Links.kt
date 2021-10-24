package com.example.testapplication.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Links(
    val mission_patch: String,
    val mission_patch_small: String,
    val article_link: String,
    val wikipedia: String,
    val video_link: String,
): Parcelable
{

}
