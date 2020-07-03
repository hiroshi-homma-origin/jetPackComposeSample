package com.kotlin.pagecurl.presentation.store

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.core.globalPosition
import androidx.ui.core.onChildPositioned
import androidx.ui.foundation.ScrollerPosition
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.Column
import androidx.ui.layout.Spacer
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.layout.preferredHeight
import androidx.ui.material.Card
import androidx.ui.unit.dp
import com.kotlin.pagecurl.domainobject.model.colors
import com.kotlin.pagecurl.domainobject.state.CurlViewStatus
import com.kotlin.pagecurl.domainobject.state.setScrollStoreOffset
import timber.log.Timber

@Composable
fun StoreComponent() {
    VerticalScroller(
        modifier = Modifier.onChildPositioned {
            Timber.d("check_condition3:${it.globalPosition}")
            setScrollStoreOffset(it)
        },
        scrollerPosition = ScrollerPosition(CurlViewStatus.offsetYStore)
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
