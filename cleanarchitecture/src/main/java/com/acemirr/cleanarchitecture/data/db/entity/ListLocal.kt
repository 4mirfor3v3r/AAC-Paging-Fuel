package com.acemirr.cleanarchitecture.data.db.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class ListLocal(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id:Int,
    @SerializedName("nama") @Expose
    val name: String,
    @SerializedName("lokasi") @Expose
    val location: String,
    @SerializedName("deskripsi") @Expose
    val description: String,
    @SerializedName("thumbnail") @Expose
    val thumbnail: String,
    @SerializedName("gambar") @Expose
    val image: String
) : Parcelable