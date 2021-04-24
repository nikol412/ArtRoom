package com.nikol412.artroom.data.response

import com.google.gson.annotations.SerializedName

data class ArtworksListResponse(
    @SerializedName("pagination")
    val pagination: Pagination,

    @SerializedName("data")
    val data: List<ArtWorkData>,

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

data class ArtworkObject(
    val apiLink: String?,
    val artistIds: List<Int>?,
    val artistTitle: String?,
    val dateDisplay: String?,
    val dimensions: String?,
    val id: Int,
    val imageId: String?,
    val title: String?,
    val imageConfig: String?
) {
    companion object {
        fun convertFromArtWorkData(data: ArtWorkData, imageConfig: String?): ArtworkObject {
            return ArtworkObject(
                data.apiLink,
                data.artistIds,
                data.artistTitle,
                data.dateDisplay,
                data.dimensions,
                data.id,
                data.imageId,
                data.title,
                imageConfig
            )
        }
    }
}

fun ArtworksListResponse.toArtworkObjectList(): List<ArtworkObject> {
    return this.data.map { data ->
        ArtworkObject.convertFromArtWorkData(
            data,
            this.imageConfig.imageUrl
        )
    }
}