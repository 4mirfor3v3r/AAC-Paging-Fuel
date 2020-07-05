package com.acemirr.cleanarchitecture.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class PagingResponse(
    @SerializedName("articles") val news: List<News>
):Parcelable