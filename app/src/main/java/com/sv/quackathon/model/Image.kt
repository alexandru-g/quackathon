package com.sv.quackathon.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Image(
    val id: String = UUID.randomUUID().toString(),
    val imageResource: String,
    val labels: List<String> = listOf(),
    val comments: List<String> = listOf(),
    val likesCount: Int,
    val dislikesCount: Int
) : Parcelable {
    companion object {
        fun buildFromSnapshot(data: Map<String, Any>): Image =
            Image(
                data["id"] as? String ?: "",
                data["imageResource"] as? String ?: "",
                data["labels"] as? List<String> ?: listOf(),
                data["comments"] as? List<String> ?: listOf(),
                data["likesCount"] as? Int ?: 0,
                data["dislikesCount"] as? Int ?: 0
            )
    }
}
