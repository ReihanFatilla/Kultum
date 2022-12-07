package com.reift.kultum.utils

import com.reift.core.domain.model.Kultum

object ShareMessage {
    fun generateMessage(kultum: Kultum): String{
        return "Assalamualaikum, here is a Kultum about ${kultum.caption} from ${kultum.creator}\nWatch it on \"Kultum\" Application"
    }
}