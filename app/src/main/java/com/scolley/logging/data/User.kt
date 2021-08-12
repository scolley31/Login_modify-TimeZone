package com.scolley.logging.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(

        val error: String? = null,
        val objectId: String? = null,
        val username: String? = null,
        val code: String? = null,
        val isVerifiedReportEmail: Boolean,
        val reportEmail: String? = null,
        val createdAt: String? = null,
        val updatedAt: String? = null,
        var timezone: Int,
        val parameter: Int,
        val number: Int,
        val photo: String? = null,
        val sessionToken: String? = null

): Parcelable {

}

