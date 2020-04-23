package com.acemirr.training_task_1.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class PagingModel(
    val idPaging: Int,
    val message: String
): Parcelable