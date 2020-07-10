package com.acemirr.cleanarchitecture.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "list")
data class LocalListModel(
    @PrimaryKey(autoGenerate = true)
    var id : Long?,
    @SerializedName("name") @Expose
    val name: String,
    @SerializedName("location") @Expose
    val location: String,
    @SerializedName("description") @Expose
    val description: String,
    @SerializedName("thumbnail") @Expose
    val thumbnail: String,
    @SerializedName("image") @Expose
    val image: String
)