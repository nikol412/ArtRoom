package com.nikol412.artroom.data.response


import com.google.gson.annotations.SerializedName

data class SearchResponseItem(
    @SerializedName("api_link")
    val apiLink: String,
    @SerializedName("api_model")
    val apiModel: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_boosted")
    val isBoosted: Boolean,
    @SerializedName("_score")
    val score: Any,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail,
    @SerializedName("timestamp")
    val timestamp: String,
    @SerializedName("title")
    val title: String
)

data class Thumbnail(
    @SerializedName("alt_text")
    val altText: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("lqip")
    val lqip: String,
    @SerializedName("width")
    val width: Int
)