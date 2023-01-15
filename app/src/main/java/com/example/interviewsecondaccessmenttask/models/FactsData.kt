package com.example.interviewsecondaccessmenttask.models


import com.google.gson.annotations.SerializedName

data class FactsData(
    @SerializedName("fact")
    var fact: String,
    @SerializedName("length")
    var length: Int
)