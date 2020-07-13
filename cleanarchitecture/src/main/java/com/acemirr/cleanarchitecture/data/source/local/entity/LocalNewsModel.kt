package com.acemirr.cleanarchitecture.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
@Entity(tableName = "paging")
data class LocalNewsModel(
    @PrimaryKey(autoGenerate = true)
    val id:Long?,
    @SerializedName("title")
    val title: String,
    @SerializedName("urlToImage") val image: String?
): Parcelable