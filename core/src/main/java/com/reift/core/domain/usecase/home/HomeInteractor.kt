package com.reift.core.domain.usecase.home

import androidx.lifecycle.LiveData
import com.reift.core.domain.model.Comments
import com.reift.core.domain.model.Kultum
import com.reift.core.domain.repository.HomeRepository

class HomeInteractor(
    val homeRepository: HomeRepository
): HomeUseCase {
    override fun getKultumForYou(): LiveData<List<Kultum>> {
        return homeRepository.getKultumForYou()
    }

    override fun addHelpfulKultum(kultum: Kultum) {
        homeRepository.addHelpfulKultum(kultum)
    }

    override fun removeHelpfulKultum(kultum: Kultum) {
        homeRepository.removeHelpfulKultum(kultum)
    }

    override fun addComment(comment: Comments, urlKultum: String) {
        homeRepository.addComment(comment, urlKultum)
    }
}