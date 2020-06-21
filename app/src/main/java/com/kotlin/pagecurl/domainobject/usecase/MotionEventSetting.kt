package com.kotlin.pagecurl.domainobject.usecase

import android.os.SystemClock
import android.view.KeyEvent.ACTION_DOWN
import android.view.KeyEvent.ACTION_UP
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_MOVE
import com.kotlin.pagecurl.viewExt.curl.base.CurlView
import kotlinx.coroutines.delay

class MotionEventSetting {
    companion object {

        private val downTime = SystemClock.uptimeMillis()
        private val eventTime = SystemClock.uptimeMillis() + 1
        private var eventNext1 = MotionEvent.obtain(downTime, eventTime, ACTION_DOWN, 1014.0f, 1220.0f, 0)
        private var eventNext2 = MotionEvent.obtain(eventTime + 1, eventTime + 1, ACTION_MOVE, 1009.2654f, 1217.0f, 0)
        private var eventNext3 = MotionEvent.obtain(eventTime + 1, eventTime + 1, ACTION_MOVE, 986.9399f, 1206.0f, 0)
        private var eventNext4 = MotionEvent.obtain(eventTime + 1, eventTime + 1, ACTION_MOVE, 972.1172f, 1194.0f, 0)
        private var eventNext5 = MotionEvent.obtain(eventTime + 1, eventTime + 1, ACTION_MOVE, 941.6948f, 1169.0f, 0)
        private var eventNext6 = MotionEvent.obtain(eventTime + 1, eventTime + 1, ACTION_MOVE, 887.5026f, 1131.0f, 0)
        private var eventNext7 = MotionEvent.obtain(eventTime + 1, eventTime + 1, ACTION_MOVE, 820.578f, 1102.0f, 0)
        private var eventNext8 = MotionEvent.obtain(eventTime + 1, eventTime + 1, ACTION_MOVE, 732.6864f, 1076.0f, 0)
        private var eventNext9 = MotionEvent.obtain(eventTime + 1, eventTime + 1, ACTION_MOVE, 619.1899f, 1062.0f, 0)
        private var eventNext10 = MotionEvent.obtain(eventTime + 1, eventTime + 1, ACTION_MOVE, 491.8622f, 1069.0f, 0)
        private var eventNext11 = MotionEvent.obtain(eventTime + 1, eventTime + 1, ACTION_MOVE, 375.73407f, 1092.0f, 0)
        private var eventNext12 = MotionEvent.obtain(eventTime + 1, eventTime + 1, ACTION_MOVE, 252.71312f, 1126.0f, 0)
        private var eventNext13 = MotionEvent.obtain(eventTime + 1, eventTime + 1, ACTION_MOVE, 158.2738f, 1168.0f, 0)
        private var eventNext14 = MotionEvent.obtain(eventTime + 1, eventTime + 1, ACTION_MOVE, 156.0f, 1170.0f, 0)
        private var eventNext15 = MotionEvent.obtain(eventTime + 1, eventTime + 1, ACTION_UP, 156.0f, 1170.0f, 0)

        private var eventBack1 = MotionEvent.obtain(downTime, eventTime, ACTION_DOWN, 100.0f, 1340.0f, 0)
        private var eventBack2 = MotionEvent.obtain(eventTime + 1, eventTime + 1, ACTION_MOVE, 100.0f, 1340.0f, 0)
        private var eventBack3 = MotionEvent.obtain(eventTime + 1, eventTime + 1, ACTION_MOVE, 100.0f, 1340.0f, 0)
        private var eventBack4 = MotionEvent.obtain(eventTime + 1, eventTime + 1, ACTION_MOVE, 108.0f, 1334.0f, 0)
        private var eventBack5 = MotionEvent.obtain(eventTime + 1, eventTime + 1, ACTION_MOVE, 138.0f, 1322.0f, 0)
        private var eventBack6 = MotionEvent.obtain(eventTime + 1, eventTime + 1, ACTION_MOVE, 179.0f, 1304.0f, 0)
        private var eventBack7 = MotionEvent.obtain(eventTime + 1, eventTime + 1, ACTION_MOVE, 239.0f, 1283.0f, 0)
        private var eventBack8 = MotionEvent.obtain(eventTime + 1, eventTime + 1, ACTION_MOVE, 308.0f, 1261.0f, 0)
        private var eventBack9 = MotionEvent.obtain(eventTime + 1, eventTime + 1, ACTION_MOVE, 396.0f, 1240.0f, 0)
        private var eventBack10 = MotionEvent.obtain(eventTime + 1, eventTime + 1, ACTION_MOVE, 490.0f, 1222.0f, 0)
        private var eventBack11 = MotionEvent.obtain(eventTime + 1, eventTime + 1, ACTION_MOVE, 590.0f, 1210.0f, 0)
        private var eventBack12 = MotionEvent.obtain(eventTime + 1, eventTime + 1, ACTION_MOVE, 719.0f, 1206.0f, 0)
        private var eventBack13 = MotionEvent.obtain(eventTime + 1, eventTime + 1, ACTION_MOVE, 796.0f, 1206.0f, 0)
        private var eventBack14 = MotionEvent.obtain(eventTime + 1, eventTime + 1, ACTION_MOVE, 796.0f, 1206.0f, 0)
        private var eventBack15 = MotionEvent.obtain(eventTime + 1, eventTime + 1, ACTION_UP, 796.0f, 1206.0f, 0)

        private var eventLandNext = MotionEvent.obtain(1, 1, ACTION_DOWN, 1280.0f, 200.0f, 0)
        private var eventLandNext1 = MotionEvent.obtain(1, 1, ACTION_MOVE, 1080.0f, 200.0f, 0)
        private var eventLandNext2 = MotionEvent.obtain(1, 1, ACTION_MOVE, 500.0f, 200.0f, 0)
        private var eventLandNext3 = MotionEvent.obtain(1, 1, ACTION_MOVE, 320.0f, 200.0f, 0)
        private var eventLandNext4 = MotionEvent.obtain(1, 1, ACTION_MOVE, 220.0f, 200.0f, 0)
        private var eventLandNext5 = MotionEvent.obtain(1, 1, ACTION_UP, 220.0f, 200.0f, 0)

        private var eventLandBack = MotionEvent.obtain(1, 1, ACTION_DOWN, 100.0f, 200.0f, 0)
        private var eventLandBack1 = MotionEvent.obtain(1, 1, ACTION_MOVE, 100.0f, 200.0f, 0)
        private var eventLandBack2 = MotionEvent.obtain(1, 1, ACTION_MOVE, 320.0f, 200.0f, 0)
        private var eventLandBack3 = MotionEvent.obtain(1, 1, ACTION_MOVE, 500.0f, 200.0f, 0)
        private var eventLandBack4 = MotionEvent.obtain(1, 1, ACTION_MOVE, 1080.0f, 200.0f, 0)
        private var eventLandBack5 = MotionEvent.obtain(1, 1, ACTION_UP, 1280.0f, 200.0f, 0)
        suspend fun motionEventPortNextImp(mCurlView: CurlView) {
            mCurlView.dispatchTouchEvent(eventNext1)
            mCurlView.dispatchTouchEvent(eventNext2)
            delay(10)
            mCurlView.dispatchTouchEvent(eventNext3)
            mCurlView.dispatchTouchEvent(eventNext4)
            delay(10)
            mCurlView.dispatchTouchEvent(eventNext5)
            mCurlView.dispatchTouchEvent(eventNext6)
            delay(10)
            mCurlView.dispatchTouchEvent(eventNext7)
            mCurlView.dispatchTouchEvent(eventNext8)
            delay(10)
            mCurlView.dispatchTouchEvent(eventNext9)
            mCurlView.dispatchTouchEvent(eventNext10)
            delay(10)
            mCurlView.dispatchTouchEvent(eventNext11)
            mCurlView.dispatchTouchEvent(eventNext12)
            delay(10)
            mCurlView.dispatchTouchEvent(eventNext13)
            mCurlView.dispatchTouchEvent(eventNext14)
            mCurlView.dispatchTouchEvent(eventNext15)
        }

        suspend fun motionEventPortBackImp(mCurlView: CurlView) {
            mCurlView.dispatchTouchEvent(eventBack1)
            mCurlView.dispatchTouchEvent(eventBack2)
            delay(10)
            mCurlView.dispatchTouchEvent(eventBack3)
            mCurlView.dispatchTouchEvent(eventBack4)
            delay(10)
            mCurlView.dispatchTouchEvent(eventBack5)
            mCurlView.dispatchTouchEvent(eventBack6)
            delay(10)
            mCurlView.dispatchTouchEvent(eventBack7)
            mCurlView.dispatchTouchEvent(eventBack8)
            delay(10)
            mCurlView.dispatchTouchEvent(eventBack9)
            mCurlView.dispatchTouchEvent(eventBack10)
            delay(10)
            mCurlView.dispatchTouchEvent(eventBack11)
            mCurlView.dispatchTouchEvent(eventBack12)
            delay(10)
            mCurlView.dispatchTouchEvent(eventBack13)
            mCurlView.dispatchTouchEvent(eventBack14)
            mCurlView.dispatchTouchEvent(eventBack15)
        }

        suspend fun motionEventLandNextImp(mCurlView: CurlView) {
            mCurlView.dispatchTouchEvent(eventLandNext)
            mCurlView.dispatchTouchEvent(eventLandNext1)
            mCurlView.dispatchTouchEvent(eventLandNext2)
            mCurlView.dispatchTouchEvent(eventLandNext3)
            mCurlView.dispatchTouchEvent(eventLandNext4)
            mCurlView.dispatchTouchEvent(eventLandNext5)
        }

        suspend fun motionEventLandBackImp(mCurlView: CurlView) {
            mCurlView.dispatchTouchEvent(eventLandBack)
            mCurlView.dispatchTouchEvent(eventLandBack1)
            mCurlView.dispatchTouchEvent(eventLandBack2)
            mCurlView.dispatchTouchEvent(eventLandBack3)
            mCurlView.dispatchTouchEvent(eventLandBack4)
            mCurlView.dispatchTouchEvent(eventLandBack5)
        }
    }
}
