package com.reift.core.domain.usecase.home

import androidx.lifecycle.LiveData
import com.reift.core.domain.model.Kultum
import org.w3c.dom.Comment

class HomeInteractor: HomeUseCase {
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