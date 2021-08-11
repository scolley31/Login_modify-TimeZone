package com.scolley.logging.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TimeZone(
        @Json(name = "timezone") val timezone: Int
): Parcelable