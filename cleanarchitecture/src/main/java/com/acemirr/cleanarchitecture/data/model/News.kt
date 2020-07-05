package com.acemirr.cleanarchitecture.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class News(
    val title: String,
    @SerializedName("urlToImage") val image: String
): Parcelable