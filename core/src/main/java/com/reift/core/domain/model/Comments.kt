package com.reift.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Comments(
    var creator: String,
    var photoUrl: String,
    var message: String,
    var date: String
): Parcelable
