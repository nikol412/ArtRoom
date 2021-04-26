package com.nikol412.artroom.data.response

import com.google.gson.annotations.SerializedName


data class SearchResponse(
    @SerializedName("pagination")
    val pagination: Pagination,

    @SerializedName("data")
    val data: ArrayList<SearchResponseItem>
)