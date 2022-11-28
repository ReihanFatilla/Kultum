package com.reift.kultum.utils

object ThumbnailUrlFormatter {
    fun format(url: String): String {
        return "https://img.youtube.com/vi/$url/0.jpg"
    }
}