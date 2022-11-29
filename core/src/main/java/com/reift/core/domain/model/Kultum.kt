package com.reift.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Kultum(
    var urlKey: String = "",
    var helpful: Map<String, String> = mapOf(),
    var comments: List<Comments> = listOf(),
    var creator: String = "",
    var caption: String = "",
    var share: Int = 0
): Parcelable
