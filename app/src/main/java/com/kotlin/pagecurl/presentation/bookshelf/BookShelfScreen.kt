package com.kotlin.pagecurl.presentation.bookshelf

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.layout.ColumnScope.weight
import androidx.ui.layout.padding
import androidx.ui.material.Surface
import androidx.ui.unit.dp
import com.kotlin.pagecurl.viewModel.BookShelfViewModel

@Composable
fun BookShelfComponent() {
    Surface(modifier = Modifier.weight(1f)) {
        Box(
            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 55.dp), gravity = ContentGravity.Center,
            children = {
            }
        )
    }
}
