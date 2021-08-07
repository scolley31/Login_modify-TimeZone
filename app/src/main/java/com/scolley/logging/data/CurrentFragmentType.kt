package com.scolley.logging.data

import com.scolley.logging.R
import com.scolley.logging.util.Util.getString

enum class CurrentFragmentType(val value: String) {

    LOGIN(""),
    INFORMATION(getString(R.string.Time_zone)),
    PERSONNEL("")

}