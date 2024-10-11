package com.easyhz.patchnote.ui.screen.defectDetail

import androidx.lifecycle.viewModelScope
import com.easyhz.patchnote.core.common.base.BaseViewModel
import com.easyhz.patchnote.domain.usecase.defect.FetchDefectUseCase
import com.easyhz.patchnote.ui.screen.defectDetail.contract.DetailIntent
import com.easyhz.patchnote.ui.screen.defectDetail.contract.DetailSideEffect
import com.easyhz.patchnote.ui.screen.defectDetail.contract.DetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DefectDetailViewModel @Inject constructor(
    private val fetchDefectUseCase: FetchDefectUseCase
): BaseViewModel<DetailState, DetailIntent, DetailSideEffect>(
    initialState = DetailState.init()
) {

    override fun handleIntent(intent: DetailIntent) {
        when(intent) {
            is DetailIntent.FetchData -> fetchDefectDetail(intent.defectId)
            is DetailIntent.NavigateToUp -> navigateToUp()
        }
    }

    private fun fetchDefectDetail(defectId: String) = viewModelScope.launch {
        fetchDefectUseCase.invoke(defectId).onSuccess {
            reduce { copy(defectItem = it) }
        }.onFailure {

        }
    }

    private fun navigateToUp() {
        postSideEffect { DetailSideEffect.NavigateToUp }
    }
}