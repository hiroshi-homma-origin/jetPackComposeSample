package com.kotlin.pagecurl.viewExt.custom

import androidx.compose.Composable
import androidx.ui.core.ContentScale
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Image
import androidx.ui.foundation.clickable
import androidx.ui.layout.preferredSize
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ripple.RippleIndication
import androidx.ui.res.vectorResource
import androidx.ui.unit.dp

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
