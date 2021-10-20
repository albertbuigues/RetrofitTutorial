package com.buigues.ortola.retrofittutorial.model

import com.google.gson.annotations.SerializedName

data class DogReponse(
    @SerializedName("message") var images: List<String>,
    @SerializedName("status") var status: String
)
