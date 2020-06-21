package com.kotlin.pagecurl.viewExt.curl.base

import android.graphics.Color
import android.graphics.PointF
import android.graphics.RectF
import android.opengl.GLSurfaceView
import android.opengl.GLU
import java.util.Vector
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class CurlRenderer(private val mObserver: Observer) : GLSurfaceView.Renderer {
    private var mBackgroundColor: Int = 0
    private val mCurlMeshes: Vector<CurlMesh> = Vector()
    private val mMargins = RectF()
    private val mPageRectLeft: RectF = RectF()
    private val mPageRectRight: RectF = RectF()
    private var mViewMode = SHOW_ONE_PAGE
    private var mViewportWidth: Int = 0
    private var mViewportHeight: Int = 0
    private val mViewRect = RectF()

    @Synchronized
    fun addCurlMesh(mesh: CurlMesh) {
        removeCurlMesh(mesh)
        mCurlMeshes.add(mesh)
    }

    fun getPageRect(page: Int): RectF? {
        if (page == PAGE_LEFT) {
            return mPageRectLeft
        } else if (page == PAGE_RIGHT) {
            return mPageRectRight
        }
        return null
    }

    @Synchronized
    override fun onDrawFrame(gl: GL10) {

        mObserver.onDrawFrame()

        gl.glClearColor(
            Color.red(mBackgroundColor) / 255f,
            Color.green(mBackgroundColor) / 255f,
            Color.blue(mBackgroundColor) / 255f,
            Color.alpha(mBackgroundColor) / 255f
        )
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT)
        gl.glLoadIdentity()

        for (i in mCurlMeshes.indices) {
            mCurlMeshes[i].onDrawFrame(gl)
        }
    }

    override fun onSurfaceChanged(gl: GL10, width: Int, height: Int) {
        gl.glViewport(0, 0, width, height)
        mViewportWidth = width
        mViewportHeight = height

        val ratio = width.toFloat() / height
        mViewRect.top = 1.0f
        mViewRect.bottom = -1.0f
        mViewRect.left = -ratio
        mViewRect.right = ratio
        updatePageRects()

        gl.glMatrixMode(GL10.GL_PROJECTION)
        gl.glLoadIdentity()
        GLU.gluOrtho2D(
            gl, mViewRect.left, mViewRect.right,
            mViewRect.bottom, mViewRect.top
        )

        gl.glMatrixMode(GL10.GL_MODELVIEW)
        gl.glLoadIdentity()
    }

    override fun onSurfaceCreated(gl: GL10, config: EGLConfig) {
        gl.glClearColor(0f, 0f, 0f, 1f)
        gl.glShadeModel(GL10.GL_SMOOTH)
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST)
        gl.glHint(GL10.GL_LINE_SMOOTH_HINT, GL10.GL_NICEST)
        gl.glHint(GL10.GL_POLYGON_SMOOTH_HINT, GL10.GL_NICEST)
        gl.glEnable(GL10.GL_LINE_SMOOTH)
        gl.glDisable(GL10.GL_DEPTH_TEST)
        gl.glDisable(GL10.GL_CULL_FACE)

        mObserver.onSurfaceCreated()
    }

    @Synchronized
    fun removeCurlMesh(mesh: CurlMesh) {
        while (mCurlMeshes.remove(mesh)) {
            // NOP
        }
    }

    fun setBackgroundColor(color: Int) {
        mBackgroundColor = color
    }

    @Synchronized
    fun setMargins(
        left: Float,
        top: Float,
        right: Float,
        bottom: Float
    ) {
        mMargins.left = left
        mMargins.top = top
        mMargins.right = right
        mMargins.bottom = bottom
        updatePageRects()
    }

    @Synchronized
    fun setViewMode(viewmode: Int) {
        if (viewmode == SHOW_ONE_PAGE) {
            mViewMode = viewmode
            updatePageRects()
        } else if (viewmode == SHOW_TWO_PAGES) {
            mViewMode = viewmode
            updatePageRects()
        }
    }

    fun translate(pt: PointF) {
        pt.x = mViewRect.left + mViewRect.width() * pt.x / mViewportWidth
        pt.y = mViewRect.top - -mViewRect.height() * pt.y / mViewportHeight
    }

    private fun updatePageRects() {
        if (mViewRect.width() == 0f || mViewRect.height() == 0f) {
            return
        } else if (mViewMode == SHOW_ONE_PAGE) {
            mPageRectRight.set(mViewRect)
            mPageRectRight.left += mViewRect.width() * mMargins.left
            mPageRectRight.right -= mViewRect.width() * mMargins.right
            mPageRectRight.top += mViewRect.height() * mMargins.top
            mPageRectRight.bottom -= mViewRect.height() * mMargins.bottom

            mPageRectLeft.set(mPageRectRight)
            mPageRectLeft.offset(-mPageRectRight.width(), 0f)

            val bitmapW = (
                mPageRectRight.width() * mViewportWidth / mViewRect
                    .width()
                ).toInt()
            val bitmapH = (
                mPageRectRight.height() * mViewportHeight / mViewRect
                    .height()
                ).toInt()
            mObserver.onPageSizeChanged(bitmapW, bitmapH)
        } else if (mViewMode == SHOW_TWO_PAGES) {
            mPageRectRight.set(mViewRect)
            mPageRectRight.left += mViewRect.width() * mMargins.left
            mPageRectRight.right -= mViewRect.width() * mMargins.right
            mPageRectRight.top += mViewRect.height() * mMargins.top
            mPageRectRight.bottom -= mViewRect.height() * mMargins.bottom

            mPageRectLeft.set(mPageRectRight)
            mPageRectLeft.right = (mPageRectLeft.right + mPageRectLeft.left) / 2
            mPageRectRight.left = mPageRectLeft.right

            val bitmapW = (
                mPageRectRight.width() * mViewportWidth / mViewRect
                    .width()
                ).toInt()
            val bitmapH = (
                mPageRectRight.height() * mViewportHeight / mViewRect
                    .height()
                ).toInt()
            mObserver.onPageSizeChanged(bitmapW, bitmapH)
        }
    }

    interface Observer {
        fun onDrawFrame()

        fun onPageSizeChanged(width: Int, height: Int)

        fun onSurfaceCreated()
    }

    companion object {
        const val PAGE_LEFT = 1
        const val PAGE_RIGHT = 2

        const val SHOW_ONE_PAGE = 1
        const val SHOW_TWO_PAGES = 2
    }
}
