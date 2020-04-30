package com.acemirr.training_task_1.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

//@Keep
//@Parcelize
//data class PagingModel(
//    val idPaging: Int,
//    val message: String
//): Parcelable

@Keep
@Parcelize
data class Response(
    @SerializedName("articles") val news: List<News>
):Parcelable

@Keep
@Parcelize
data class News(
    val title: String,
    @SerializedName("urlToImage") val image: String
):Parcelable