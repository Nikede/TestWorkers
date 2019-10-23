package com.nikede.test.data.network.response


import com.google.gson.annotations.SerializedName
import com.nikede.test.data.db.entities.Worker

data class WorkersResponse(
    @SerializedName("response")
    val workers: List<Worker>
)