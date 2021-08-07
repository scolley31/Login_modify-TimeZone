package com.scolley.logging.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(

    val error: String? = null,
    val objectId: String,
    val username: String,
    val code: String,
    val isVerifiedReportEmail: Boolean,
    val reportEmail: String,
    val createdAt: String,
    val updatedAt: String,
    val timezone: Int,
    val parameter: Int,
    val number: Int,
    val photo: String,
    val sessionToken: String

): Parcelable {

}

