package com.scolley.logging.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TrafficNews(

        val error: String? = null,
        val updateTime: String? = null,
        val News: List<Information>

): Parcelable {

}

@Parcelize
data class Information(

        val chtmessage: String? = null,
        val engmessage: String? = null,
        val starttime: String? = null,
        val endtime: String? = null,
        val updatetime: String? = null,
        val content: String? = null,
        val url: String? = null

): Parcelable {

}