package com.kotlin.pagecurl.viewExt.curl.interfaces

import com.kotlin.pagecurl.viewExt.curl.base.CurlView

class SizeChangedObserver(
    private val curlView: CurlView
) : CurlView.SizeChangedObserver {
    override fun onSizeChanged(width: Int, height: Int) {
        if (width > height) {
            curlView.setViewMode(CurlView.SHOW_TWO_PAGES)
        } else {
            curlView.setViewMode(CurlView.SHOW_ONE_PAGE)
        }
    }
}
