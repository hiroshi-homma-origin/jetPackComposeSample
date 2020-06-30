package com.kotlin.pagecurl.presentation.common

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.Composable
import androidx.compose.FrameManager
import androidx.compose.onCommit
import androidx.compose.state
import androidx.ui.core.ContextAmbient
import androidx.ui.core.WithConstraints
import androidx.ui.foundation.Image
import androidx.ui.graphics.ImageAsset
import androidx.ui.graphics.asImageAsset
import androidx.ui.unit.IntPx
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun GlideImage(
    model: Any,
    onImageReady: (() -> Unit)? = null,
    customize: RequestBuilder<Bitmap>.() -> RequestBuilder<Bitmap> = { this }
) {
    WithConstraints {
        val image = state<ImageAsset?> { null }
        val context = ContextAmbient.current
//        val cacheDrawable = state<Drawable?> { null }

        onCommit(model) {
            val glide = Glide.with(context)
            var target: CustomTarget<Bitmap>? = null
            val job = CoroutineScope(Dispatchers.Main).launch {
                target = object : CustomTarget<Bitmap>() {
                    override fun onLoadCleared(placeholder: Drawable?) {
                        image.value = null
                    }

                    override fun onResourceReady(bitmap: Bitmap, transition: Transition<in Bitmap>?) {
//                        cacheDrawable.value = BitmapDrawable(context.resources, bitmap)
                        FrameManager.framed {
                            image.value = bitmap.asImageAsset()
                            onImageReady?.invoke()
                        }
                    }
                }

                val width =
                    if (constraints.maxWidth > IntPx.Zero && constraints.maxWidth < IntPx.Infinity) {
                        constraints.maxWidth.value
                    } else {
                        Target.SIZE_ORIGINAL
                    }

                val height =
                    if (constraints.maxHeight > IntPx.Zero && constraints.maxHeight < IntPx.Infinity) {
                        constraints.maxHeight.value
                    } else {
                        Target.SIZE_ORIGINAL
                    }

                glide
                    .asBitmap()
                    .load(model)
                    .override(width, height)
                    .let(customize)
                    .into(target!!)
            }

            onDispose {
                image.value = null
                glide.clear(target)
                job.cancel()
            }
        }

        val theImage = image.value
//        Timber.d("check_cacheImageList:$cacheDrawable")
        if (theImage != null) {
            Image(asset = theImage)
        }
    }
}
