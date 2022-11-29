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

    override fun getKultumDetail(urlKultum: String): LiveData<Kultum> {
        return homeRepository.getKultumDetail(urlKultum)
    }

    override fun isKultumHelpfuled(urlKultum: String): LiveData<Boolean> {
        return homeRepository.isKultumHelpfuled(urlKultum)
    }

    override fun addHelpfulKultum(urlKultum: String) {
        homeRepository.addHelpfulKultum(urlKultum)
    }

    override fun removeHelpfulKultum(urlKultum: String) {
        homeRepository.removeHelpfulKultum(urlKultum)
    }

    override fun addComment(comment: Comments, urlKultum: String) {
        homeRepository.addComment(comment, urlKultum)
    }
}