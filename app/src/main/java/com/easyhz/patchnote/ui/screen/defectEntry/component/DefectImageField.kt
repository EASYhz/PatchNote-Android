package com.easyhz.patchnote.ui.screen.defectEntry.component

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.CrossFade
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.easyhz.patchnote.R
import com.easyhz.patchnote.core.designSystem.util.extension.noRippleClickable
import com.easyhz.patchnote.ui.theme.MainBackground
import com.easyhz.patchnote.ui.theme.PlaceholderText
import com.easyhz.patchnote.ui.theme.SemiBold18
import com.easyhz.patchnote.ui.theme.SubBackground

@Composable
fun DefectImageField(
    modifier: Modifier = Modifier,
    images: List<Uri>,
    onClickAdd: () -> Unit,
    onClickDelete: (Uri) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.defect_entry_image),
                style = SemiBold18
            )

            Icon(
                modifier = Modifier.size(32.dp).noRippleClickable { onClickAdd() },
                painter = painterResource(R.drawable.ic_add),
                contentDescription = "add",
            )
        }

        if (images.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(SubBackground)
                    .noRippleClickable { onClickAdd() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.defect_entry_image_placeholder),
                    style = SemiBold18,
                    color = PlaceholderText
                )
            }
        } else {
            LazyRow {
                items(images) { item ->
                    ImageItem(
                        imageUrl = item,
                        onClickDelete = { onClickDelete(item) }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun ImageItem(
    modifier: Modifier = Modifier,
    imageUrl: Uri,
    onClickDelete: () -> Unit
) {
    Box(
        modifier = modifier
            .size(120.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClickDelete() }
    ) {
        GlideImage(
            modifier = Modifier.size(120.dp),
            model = imageUrl,
            contentDescription = null,
            loading = placeholder(ColorPainter(SubBackground)),
            failure = placeholder(ColorPainter(SubBackground)),
            contentScale = ContentScale.Crop,
            transition = CrossFade
        )
        Box(
            modifier = Modifier
                .height(32.dp)
                .width(120.dp)
                .align(Alignment.TopCenter)
                .background(MainBackground.copy(alpha = 0.6f))
        ) {
            Icon(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(2.dp),
                painter = painterResource(R.drawable.ic_close),
                contentDescription = "Close"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefectImageFieldPreview() {
    DefectImageField(
        images = emptyList(),
        onClickAdd = {},
    ) {

    }

}