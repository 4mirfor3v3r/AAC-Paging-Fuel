package com.acemirr.training_task_1.ui.menu.paging.model

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