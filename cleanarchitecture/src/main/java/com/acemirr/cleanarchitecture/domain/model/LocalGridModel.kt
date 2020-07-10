package com.acemirr.cleanarchitecture.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
@Entity(tableName = "grid")
data class LocalGridModel(
    @PrimaryKey(autoGenerate = true) @SerializedName("id") @Expose val id:Long?,
    @SerializedName("caption") @Expose val caption: String,
    @SerializedName("thumbnail") @Expose val thumbnail: String,
    @SerializedName("image") @Expose val image: String
): Parcelable