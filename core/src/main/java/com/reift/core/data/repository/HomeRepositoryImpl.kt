package com.reift.core.data.repository

import androidx.lifecycle.LiveData
import com.reift.core.domain.model.Kultum
import com.reift.core.domain.repository.HomeRepository
import org.w3c.dom.Comment

class HomeRepositoryImpl(

): HomeRepository {
    override fun getKultumForYou(): LiveData<List<Kultum>> {
        TODO("Not yet implemented")
    }

    override fun addHelpfulKultum(kultum: Kultum) {
        TODO("Not yet implemented")
    }

    override fun removeHelpfulKultum(kultum: Kultum) {
        TODO("Not yet implemented")
    }

    override fun addComment(comment: Comment) {
        TODO("Not yet implemented")
    }

}