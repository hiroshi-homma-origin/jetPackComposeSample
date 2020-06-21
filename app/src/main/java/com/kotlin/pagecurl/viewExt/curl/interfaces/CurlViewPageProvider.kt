package com.kotlin.pagecurl.viewExt.curl.interfaces

import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import androidx.core.content.res.ResourcesCompat
import com.kotlin.pagecurl.R
import com.kotlin.pagecurl.viewExt.curl.base.CurlPage
import com.kotlin.pagecurl.viewExt.curl.base.CurlView

class CurlViewPageProvider(
    private val resources: Resources,
    private val orientation: Int?
) : CurlView.PageProvider {
    private val mBitmapIds = intArrayOf(
        R.drawable.kimetsu1, R.drawable.kimetsu2, R.drawable.kimetsu3, R.drawable.kimetsu4,
        R.drawable.kimetsu5, R.drawable.kimetsu6, R.drawable.kimetsu7, R.drawable.kimetsu8,
        R.drawable.kimetsu7, R.drawable.kimetsu6, R.drawable.kimetsu5, R.drawable.kimetsu4,
        R.drawable.kimetsu3, R.drawable.kimetsu2, R.drawable.kimetsu1, R.drawable.kimetsu2,
        R.drawable.kimetsu3, R.drawable.kimetsu4, R.drawable.kimetsu5, R.drawable.kimetsu6,
        R.drawable.kimetsu7, R.drawable.kimetsu8, R.drawable.kimetsu1, R.drawable.kimetsu2,
        R.drawable.kimetsu3, R.drawable.kimetsu4, R.drawable.kimetsu5, R.drawable.kimetsu6,
        R.drawable.kimetsu7, R.drawable.kimetsu8, R.drawable.kimetsu1, R.drawable.kimetsu2,
        R.drawable.kimetsu3, R.drawable.kimetsu4, R.drawable.kimetsu5, R.drawable.kimetsu6,
        R.drawable.kimetsu7, R.drawable.kimetsu8, R.drawable.kimetsu1, R.drawable.kimetsu2,
        R.drawable.kimetsu3, R.drawable.kimetsu4, R.drawable.kimetsu5, R.drawable.kimetsu6,
        R.drawable.kimetsu5, R.drawable.kimetsu4, R.drawable.kimetsu3, R.drawable.kimetsu2,
        R.drawable.kimetsu1
    )

    override val pageCount: Int
        get() = when (orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> mBitmapIds.size - mBitmapIds.size / 2
            else -> mBitmapIds.size
        }

    private fun loadBitmap(width: Int, height: Int, index: Int): Bitmap {
        val b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val c = Canvas(b)

        val d = ResourcesCompat.getDrawable(resources, mBitmapIds[index], null)
        d?.let {
            val margin = 0
            val border = 3
            val r = Rect(margin, margin, width - margin, height - margin)

            var imageWidth = r.width() - border * 2
            var imageHeight = imageWidth * d.intrinsicHeight / d.intrinsicWidth
            if (imageHeight > r.height() - border * 2) {
                imageHeight = r.height() - border * 2
                imageWidth = imageHeight * d.intrinsicWidth / d.intrinsicHeight
            }

            r.left += (r.width() - imageWidth) / 2 - border
            r.right = r.left + imageWidth + border + border
            r.top += (r.height() - imageHeight) / 2 - border
            r.bottom = r.top + imageHeight + border + border

            val p = Paint()
            c.drawRect(r, p)
            r.left += border
            r.right -= border
            r.top += border
            r.bottom -= border

            d.bounds = r
            d.draw(c)
        }
        return b
    }

    override fun updatePage(page: CurlPage, width: Int, height: Int, index: Int) {
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            when (index) {
                0 -> {
                    val front = loadBitmap(width, height, index)
                    page.setTexture(front, CurlPage.SIDE_FRONT)
                    val back = loadBitmap(width, height, index + 1)
                    page.setTexture(back, CurlPage.SIDE_BACK)
                }
                mBitmapIds.size / 2 -> {
                    val front = loadBitmap(width, height, index * 2)
                    page.setTexture(front, CurlPage.SIDE_FRONT)
                }
                else -> {
                    val front = loadBitmap(width, height, index * 2)
                    page.setTexture(front, CurlPage.SIDE_FRONT)
                    val back = loadBitmap(width, height, index * 2 + 1)
                    page.setTexture(back, CurlPage.SIDE_BACK)
                }
            }
        } else {
            val front = loadBitmap(width, height, index)
            page.setTexture(front, CurlPage.SIDE_FRONT)
        }
    }
}
