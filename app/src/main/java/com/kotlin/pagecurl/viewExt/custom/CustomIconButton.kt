package com.kotlin.pagecurl.viewExt.custom

import androidx.compose.foundation.Box
import androidx.compose.foundation.ContentGravity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ripple.RippleIndication
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp

@Composable
fun CustomIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    avatar: Int
) {
    val rippleOpacity = 0.16f
    Box(
        modifier = modifier
            .clickable(
                onClick = onClick,
                indication = RippleIndication(
                    color = MaterialTheme.colors.onSurface.copy(alpha = rippleOpacity)
                )
            ),
        gravity = ContentGravity.Center,
        children = {
            Image(
                asset = vectorResource(id = avatar),
                modifier = Modifier.preferredSize(320.dp),
                contentScale = ContentScale.Fit
            )
        }
    )
}
