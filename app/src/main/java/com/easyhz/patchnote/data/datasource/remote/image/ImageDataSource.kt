package com.easyhz.patchnote.data.datasource.remote.image

import android.net.Uri

interface ImageDataSource {
    suspend fun uploadImage(pathId: String, imageUri: Uri, imageName: String): Result<String>
}