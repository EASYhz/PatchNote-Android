package com.easyhz.patchnote.data.datasource.category

import com.easyhz.patchnote.data.model.category.response.CategoryResponse

interface CategoryDataSource {
    suspend fun fetchCategory(): Result<CategoryResponse>
}