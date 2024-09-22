package com.easyhz.patchnote.data.repository.category

import com.easyhz.patchnote.core.model.category.Category

interface CategoryRepository {
    suspend fun fetchCategory(): Result<List<Category>>
}