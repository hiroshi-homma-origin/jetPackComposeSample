package com.kotlin.pagecurl.presentation.common

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.FrameManager
import androidx.compose.runtime.onCommit
import androidx.compose.runtime.state
import androidx.compose.ui.WithConstraints
import androidx.compose.ui.graphics.ImageAsset
import androidx.compose.ui.graphics.asImageAsset
import androidx.compose.ui.platform.ContextAmbient
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.kotlin.pagecurl.R
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
        val context = ContextAmbient.current
        val pBitmap = context.resources.getDrawable(R.drawable.placeholder, null).toBitmap()
        val image = state<ImageAsset?> { pBitmap.asImageAsset() }
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

                glide
                    .asBitmap()
                    .load(model)
//                    .override(width, height)
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
