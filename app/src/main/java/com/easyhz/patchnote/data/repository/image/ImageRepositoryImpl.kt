package com.easyhz.patchnote.data.repository.image

import android.content.Context
import android.net.Uri
import com.easyhz.patchnote.core.common.di.dispatcher.Dispatcher
import com.easyhz.patchnote.core.common.di.dispatcher.PatchNoteDispatchers
import com.easyhz.patchnote.core.model.image.ImageSize
import com.easyhz.patchnote.data.datasource.remote.image.ImageDataSource
import com.easyhz.patchnote.data.provider.PatchNoteFileProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    @Dispatcher(PatchNoteDispatchers.IO) private val dispatcher: CoroutineDispatcher,
    @ApplicationContext private val context: Context,
    private val imageDataSource: ImageDataSource,
) : ImageRepository {

    override suspend fun getTakePictureUri(): Result<Uri> =
        PatchNoteFileProvider.getTakePictureUri(context, dispatcher)

    override suspend fun uploadImages(pathId: String, images: List<Uri>): Result<List<String>> =
        withContext(dispatcher) {
            runCatching {
                if (images.isEmpty()) return@runCatching emptyList()
                val imageListDeferred = async {
                    images.mapIndexed { index, image ->
                        val imageUri = PatchNoteFileProvider.compressImageUriToMaxSize(context, dispatcher, image, 0.1)
                            .getOrThrow()
                        imageUri.let { uri ->
                            imageDataSource.uploadImage(
                                pathId,
                                uri,
                                "$index"
                            ).getOrThrow()
                        }
                    }
                }
                imageListDeferred.await()
            }
        }

    override suspend fun uploadThumbnail(pathId: String, imageUri: Uri): Result<String> =
        withContext(dispatcher) {
            runCatching {
                val thumbnail =
                    PatchNoteFileProvider.compressImageUriToMaxSize(context, dispatcher, imageUri, 0.05)
                        .getOrThrow()
                imageDataSource.uploadImage(
                    pathId,
                    thumbnail,
                    "thumbnail"
                ).getOrThrow()
            }
        }

    override suspend fun getImageSizes(imageUri: List<Uri>): Result<List<ImageSize>> =
        withContext(dispatcher) {
            runCatching {
                imageUri.map {
                    PatchNoteFileProvider.getImageDimensions(context, it)
                }
            }
        }

    override suspend fun rotateImage(imageUri: Uri): Result<Unit> = withContext(dispatcher) {
        runCatching {
            PatchNoteFileProvider.rotateAndSaveImage(context, imageUri)
        }
    }
}
