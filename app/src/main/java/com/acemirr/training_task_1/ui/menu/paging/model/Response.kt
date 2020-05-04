package com.acemirr.training_task_1.ui.menu.paging.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class Response(
    @SerializedName("articles") val news: List<News>
):Parcelable