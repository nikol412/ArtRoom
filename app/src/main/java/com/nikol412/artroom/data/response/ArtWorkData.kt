package com.nikol412.artroom.data.response


import com.google.gson.annotations.SerializedName

data class ArtworkResponse(
    @SerializedName("data")
    val data: ArtWorkData,

    @SerializedName("config")
    val imageConfig: ImageConfig
)
data class ArtWorkData(
    @SerializedName("api_link")
    val apiLink: String,

    @SerializedName("artist_ids")
    val artistIds: List<Int>,

    @SerializedName("artist_title")
    val artistTitle: String,

    @SerializedName("date_display")
    val dateDisplay: String,

    @SerializedName("dimensions")
    val dimensions: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("image_id")
    val imageId: String,

    @SerializedName("title")
    val title: String
)

data class ImageConfig(
    @SerializedName("iiif_url")
    val imageUrl: String
)