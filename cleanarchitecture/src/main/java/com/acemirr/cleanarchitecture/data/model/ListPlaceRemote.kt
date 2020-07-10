package com.acemirr.cleanarchitecture.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class ListPlaceRemote(
    @SerializedName("nama") @Expose
    val name: String,
    @SerializedName("lokasi") @Expose
    val location: String,
    @SerializedName("deskripsi") @Expose
    val description: String,
    @SerializedName("thumbnail") @Expose
    val thumbnail: String= "",
    @SerializedName("gambar") @Expose
    val image: String=""
) : Parcelable