package com.easyhz.patchnote.ui.screen.home.contract

import com.easyhz.patchnote.core.common.base.UiState
import com.easyhz.patchnote.core.model.configuration.Configuration
import com.easyhz.patchnote.core.model.defect.DefectItem

data class HomeState(
    val appConfiguration: Configuration,
    val isLatestVersion: Boolean,
    val defectList: List<DefectItem>,
    val isRefreshing: Boolean,
    val isShowPasswordDialog: Boolean,
    val isShowPasswordErrorDialog: Boolean,
    val isShowExportDialog: Boolean,
    val hasPassword: Boolean,
    val password: String,
    val isLoading: Boolean
): UiState() {
    companion object {
        fun init() = HomeState(
            appConfiguration = Configuration("", "", ""),
            isLatestVersion = true,
            defectList = emptyList(),
            isRefreshing = false,
            isShowPasswordDialog = false,
            isShowPasswordErrorDialog = false,
            isShowExportDialog = false,
            hasPassword = false,
            password = "",
            isLoading = false
        )
    }
}
