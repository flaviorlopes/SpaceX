package com.example.testapplication.models.requests

import com.google.gson.annotations.SerializedName

data class CharacterResult(
    @SerializedName("results")
    val CharactersList: ArrayList<Character>) {
}