package com.kotlin.pagecurl.presentation.curlViewer

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.view.MotionEvent
import android.view.View
import androidx.compose.Composable
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.graphics.Color
import androidx.ui.layout.ColumnScope.weight
import androidx.ui.layout.padding
import androidx.ui.material.Surface
import androidx.ui.unit.dp
import androidx.ui.viewinterop.AndroidView
import com.kotlin.pagecurl.R
import com.kotlin.pagecurl.viewExt.OnSwipeTouchListener
import com.kotlin.pagecurl.viewExt.curl.base.CurlView
import com.kotlin.pagecurl.viewExt.curl.interfaces.CurlViewPageProvider
import com.kotlin.pagecurl.viewExt.curl.interfaces.SizeChangedObserver
import com.kotlin.pagecurl.viewModel.CurlViewModel
import timber.log.Timber

@Composable
fun CurlViewComponent(
    curlViewModel: CurlViewModel
) {
    CurlViewLayoutView(curlViewModel)
}

@SuppressLint("ClickableViewAccessibility")
@Composable
fun CurlViewLayoutView(curlViewModel: CurlViewModel) {
    val context = ContextAmbient.current
    val resources = ContextAmbient.current.resources
    val orientation = resources.configuration.orientation

    Surface(color = Color(0xFFfffbd0.toInt()), modifier = Modifier.weight(1f)) {
        Box(
            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 55.dp), gravity = ContentGravity.Center,
            children = {
                AndroidView(resId = R.layout.curl_view) { inflateLayout ->
                    inflateLayout.also { view ->
                        val f = view.findViewById<ConstraintLayout>(R.id.constraintLayout)
                        val c = view.findViewById<CurlView>(R.id.curl)
                        val pz = view.findViewById<View>(R.id.portrait_zone)
                        val lz = view.findViewById<View>(R.id.left_zone)
                        val rz = view.findViewById<View>(R.id.right_zone)

                        Timber.d("check_f_click")
                        f.setOnClickListener {
                            curlViewModel.apply {
                                isCommandButtonOpen()
                                Timber.d("check_f_click")
                                if (isCommandButtonOpen) {
                                    pz.visibility = View.VISIBLE
                                    lz.visibility = View.VISIBLE
                                    rz.visibility = View.VISIBLE
                                } else {
                                    pz.visibility = View.GONE
                                    lz.visibility = View.GONE
                                    rz.visibility = View.GONE
                                }
                            }
                        }
                        c.also { curl ->
                            curl.setPageProvider(CurlViewPageProvider(resources, orientation))
                            curl.setSizeChangedObserver(SizeChangedObserver(curl))
                            // ここはORIENTATION_LANDSCAPEの条件分岐を書かないとダメ。
                            curl.currentIndex = curlViewModel.curlCurrentIndex
                        }
                        pz?.also { portraitZone ->
                            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                                portraitZone.visibility = View.GONE
                            }
                            portraitZone.setOnTouchListener(
                                object : OnSwipeTouchListener(context) {
                                    override fun onMotionEvent(event: MotionEvent) {
                                        if (event.x < 400) {
                                            curlViewModel.curlViewLayoutSetBack4PageMotionEvent(c, portraitZone)
                                        } else if (event.x > 500) {
                                            curlViewModel.curlViewLayoutSetNext4PageMotionEvent(c, portraitZone)
                                        }
                                    }
                                })
                        }
                        lz.setOnClickListener {
                            curlViewModel.curlViewLayoutSetBackMotionEvent(c, orientation)
                        }
                        rz.setOnClickListener {
                            curlViewModel.curlViewLayoutSetNextMotionEvent(c, orientation)
                        }
                    }
                }
            }
        )
    }
}
