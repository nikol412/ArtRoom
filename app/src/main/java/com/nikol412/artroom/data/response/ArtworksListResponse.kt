package com.nikol412.artroom.data.response

import com.google.gson.annotations.SerializedName

data class ArtworksListResponse(
    @SerializedName("pagination")
    val pagination: Pagination,

    @SerializedName("data")
    val data: ArtWorkData,

    @SerializedName("config")
    val imageConfig: ImageConfig
)

data class Pagination(
    @SerializedName("total")
    val totalArtworks: Int,

    @SerializedName("limit")
    val limit: Int,

    @SerializedName("offset")
    val offset: Int,

    @SerializedName("total_pages")
    val totalPages: Int,

    @SerializedName("current_page")
    val currentPage: Int,
)