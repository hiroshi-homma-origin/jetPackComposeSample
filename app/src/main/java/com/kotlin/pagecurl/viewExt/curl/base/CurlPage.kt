package com.kotlin.pagecurl.viewExt.curl.base

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.RectF

class CurlPage {

    private var mColorBack: Int = 0
    private var mColorFront: Int = 0
    private var mTextureBack: Bitmap? = null
    private var mTextureFront: Bitmap? = null
    var texturesChanged: Boolean = false
        private set

    init { reset() }

    fun getColor(side: Int): Int {
        return when (side) {
            SIDE_FRONT -> mColorFront
            else -> mColorBack
        }
    }

    private fun getNextHighestPO2(n: Int): Int {
        @Suppress("NAME_SHADOWING")
        var n = n
        n -= 1
        n = n or (n shr 1)
        n = n or (n shr 2)
        n = n or (n shr 4)
        n = n or (n shr 8)
        n = n or (n shr 16)
        n = n or (n shr 32)
        return n + 1
    }

    private fun getTexture(bitmap: Bitmap, textureRect: RectF): Bitmap {
        val w = bitmap.width
        val h = bitmap.height
        val newW = getNextHighestPO2(w)
        val newH = getNextHighestPO2(h)

        val bitmapTex = Bitmap.createBitmap(newW, newH, bitmap.config)
        val c = Canvas(bitmapTex)
        c.drawBitmap(bitmap, 0f, 0f, null)

        val texX = w.toFloat() / newW
        val texY = h.toFloat() / newH
        textureRect.set(0f, 0f, texX, texY)

        return bitmapTex
    }

    fun getTexture(textureRect: RectF, side: Int): Bitmap {
        return when (side) {
            SIDE_FRONT -> getTexture(mTextureFront!!, textureRect)
            else -> getTexture(mTextureBack!!, textureRect)
        }
    }

    fun hasBackTexture(): Boolean {
        return mTextureFront != mTextureBack
    }

    fun recycle() {
        if (mTextureFront != null) {
            mTextureFront!!.recycle()
        }
        mTextureFront = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565)
        mTextureFront!!.eraseColor(mColorFront)
        if (mTextureBack != null) {
            mTextureBack!!.recycle()
        }
        mTextureBack = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565)
        mTextureBack!!.eraseColor(mColorBack)
        texturesChanged = false
    }

    fun reset() {
        mColorBack = Color.WHITE
        mColorFront = Color.WHITE
        recycle()
    }

    fun setColor(color: Int, side: Int) {
        when (side) {
            SIDE_FRONT -> mColorFront = color
            SIDE_BACK -> mColorBack = color
            mColorFront -> mColorBack = color
        }
    }

    @Suppress("NAME_SHADOWING")
    fun setTexture(texture: Bitmap?, side: Int) {
        var texture = texture
        if (texture == null) {
            texture = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565)
            if (side == SIDE_BACK) {
                texture!!.eraseColor(mColorBack)
            } else {
                texture!!.eraseColor(mColorFront)
            }
        }
        when (side) {
            SIDE_FRONT -> {
                if (mTextureFront != null)
                    mTextureFront!!.recycle()
                mTextureFront = texture
            }
            SIDE_BACK -> {
                if (mTextureBack != null)
                    mTextureBack!!.recycle()
                mTextureBack = texture
            }
            SIDE_BOTH -> {
                if (mTextureFront != null)
                    mTextureFront!!.recycle()
                if (mTextureBack != null)
                    mTextureBack!!.recycle()
                mTextureBack = texture
                mTextureFront = mTextureBack
            }
        }
        texturesChanged = true
    }

    companion object {
        const val SIDE_FRONT = 1
        const val SIDE_BACK = 2
        const val SIDE_BOTH = 3
    }
}
