package com.kotlin.pagecurl.presentation.mypage

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.globalPosition
import androidx.compose.ui.onChildPositioned
import androidx.compose.ui.unit.dp
import com.kotlin.pagecurl.domainobject.model.colors
import com.kotlin.pagecurl.domainobject.state.setScrollMyPageOffset
import timber.log.Timber

@Composable
fun MyPageComponent() {
    ScrollableColumn(
        modifier = Modifier.onChildPositioned {
            Timber.d("check_condition4:${it.globalPosition}")
            setScrollMyPageOffset(it)
        },
//        scrollerPosition = ScrollerPosition(CurlViewStatus.offsetYMyPage)
    ) {
        Column {
            repeat(100) {
                Card(
                    color = colors[it % colors.size],
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.padding(8.dp)
                ) {
                    Spacer(modifier = Modifier.fillMaxWidth() + Modifier.preferredHeight(200.dp))
                }
            }
        }
    }
}
