package com.easyhz.patchnote.domain.usecase.category

import com.easyhz.patchnote.core.common.base.BaseUseCase
import com.easyhz.patchnote.core.model.dataEntry.DataEntryItem
import com.easyhz.patchnote.data.repository.category.CategoryRepository
import javax.inject.Inject

class UpdateCategoryUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
): BaseUseCase<List<DataEntryItem>, Unit>() {
    override suspend fun invoke(param: List<DataEntryItem>): Result<Unit> {
        return categoryRepository.updateCategory(param)
    }
}