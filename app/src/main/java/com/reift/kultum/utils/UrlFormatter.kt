package com.reift.kultum.utils

import java.util.regex.Pattern

object UrlFormatter {

    fun format(youTubeUrl: String): String? {
        val pattern = "http(?:s)?:\\/\\/(?:m.)?(?:www\\.)?youtu(?:\\.be\\/|(?:be-nocookie|be)\\.com\\/(?:watch|[\\w]+\\?(?:feature=[\\w]+.[\\w]+\\&)?v=|v\\/|e\\/|embed\\/|user\\/(?:[\\w#]+\\/)+))([^&#?\\n]+)"
        val compiledPattern = Pattern.compile(pattern)
        val matcher = compiledPattern.matcher(youTubeUrl)
        return if (matcher.find()) {
            matcher.group(1)
        } else if(youTubeUrl.contains("shorts")){
            val splitted = youTubeUrl.split("/")
            val index = splitted.indexOf("shorts")
            if(youTubeUrl.contains("?")){
                val unformatId = splitted[index+1]
                unformatId.substring(0, unformatId.indexOf("?"))
            } else {
                splitted[index+1]
            }
        } else {
            null
        }
    }

}