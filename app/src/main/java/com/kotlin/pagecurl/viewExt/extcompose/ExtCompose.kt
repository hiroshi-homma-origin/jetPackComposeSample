package com.kotlin.pagecurl.viewExt.extcompose

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.onCommit
import androidx.compose.setValue
import androidx.compose.state
import androidx.ui.core.Alignment
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.core.clip
import androidx.ui.core.composed
import androidx.ui.foundation.Box
import androidx.ui.foundation.Canvas
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.ColorFilter
import androidx.ui.graphics.ImageAsset
import androidx.ui.graphics.asImageAsset
import androidx.ui.graphics.drawscope.drawCanvas
import androidx.ui.graphics.vector.VectorAsset
import androidx.ui.layout.Arrangement.Start
import androidx.ui.layout.Column
import androidx.ui.layout.Row
import androidx.ui.layout.Spacer
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.layout.preferredHeight
import androidx.ui.layout.preferredHeightIn
import androidx.ui.layout.preferredWidth
import androidx.ui.material.BottomNavigation
import androidx.ui.material.BottomNavigationItem
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import androidx.ui.material.TextButton
import androidx.ui.material.icons.Icons.Filled
import androidx.ui.material.icons.filled.Call
import androidx.ui.material.icons.filled.DateRange
import androidx.ui.material.icons.filled.Face
import androidx.ui.material.icons.filled.Favorite
import androidx.ui.material.icons.filled.Home
import androidx.ui.material.icons.filled.ThumbUp
import androidx.ui.res.loadImageResource
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontFamily
import androidx.ui.unit.Dp
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.kotlin.pagecurl.domainobject.model.AppScreen
import com.kotlin.pagecurl.domainobject.model.AppScreen.Screen1
import com.kotlin.pagecurl.domainobject.model.AppScreen.Screen2
import com.kotlin.pagecurl.domainobject.model.AppScreen.Screen3
import com.kotlin.pagecurl.domainobject.model.AppScreen.Screen4
import com.kotlin.pagecurl.domainobject.model.AppScreen.Screen5
import com.kotlin.pagecurl.domainobject.model.AppScreen.Screen6
import com.kotlin.pagecurl.domainobject.model.getScreenBasedOnIndex
import com.kotlin.pagecurl.domainobject.state.CurlViewStatus
import com.kotlin.pagecurl.domainobject.state.navigateTo
import com.kotlin.pagecurl.presentation.bookshelf.BookShelfComponent
import com.kotlin.pagecurl.presentation.curlViewer.CurlViewComponent
import com.kotlin.pagecurl.presentation.curlViewer.CurlViewModel
import com.kotlin.pagecurl.presentation.home.HomeViewComponent
import com.kotlin.pagecurl.presentation.home.HomeViewModel
import com.kotlin.pagecurl.presentation.mypage.MyPageComponent
import com.kotlin.pagecurl.presentation.ranking.RankingComponent
import com.kotlin.pagecurl.presentation.store.StoreComponent
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target

@Composable
fun LocalResourceImageComponent(@DrawableRes resId: Int) {
    val image = loadImageResource(resId)
    image.resource.resource?.let {
        Image(asset = it, modifier = Modifier.preferredHeightIn(maxHeight = 200.dp))
    }
}

@Composable
fun ImageWithRoundedCorners(@DrawableRes resId: Int) {
    val image = loadImageResource(resId)
    image.resource.resource?.let {
        Box(
            modifier =
                Modifier.preferredHeight(200.dp) + Modifier.preferredWidth(200.dp) +
                    Modifier.RoundedCornerClipModifier(8.dp)
        ) {
            Image(it)
        }
    }
}

@Composable
fun NetworkImageComponentPicasso(
    url: String,
    modifier: Modifier = Modifier.fillMaxWidth() +
        Modifier.preferredHeightIn(maxHeight = 200.dp)
) {
    var image by state<ImageAsset?> { null }
    var drawable by state<Drawable?> { null }
    onCommit(url) {
        val picasso = Picasso.get()
        val target = object : Target {
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                // TODO(lmr): we could use the drawable below
                drawable = placeHolderDrawable
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                drawable = errorDrawable
            }

            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                image = bitmap?.asImageAsset()
            }
        }
        picasso
            .load(url)
            .into(target)

        onDispose {
            image = null
            drawable = null
            picasso.cancelRequest(target)
        }
    }

    val theImage = image
    val theDrawable = drawable
    if (theImage != null) {
        Box(
            modifier = modifier,
            gravity = ContentGravity.Center
        ) {
            Image(asset = theImage)
        }
    } else if (theDrawable != null) {
        Canvas(modifier = modifier) {
            drawCanvas { canvas, _ ->
                theDrawable.draw(canvas.nativeCanvas)
            }
        }
    }
}

@Composable
fun NetworkImageComponentGlide(
    url: String,
    modifier: Modifier = Modifier.fillMaxWidth() +
        Modifier.preferredHeightIn(maxHeight = 200.dp)
) {
    var image by state<ImageAsset?> { null }
    var drawable by state<Drawable?> { null }
    val context = ContextAmbient.current
    onCommit(url) {
        val glide = Glide.with(context)
        val target = object : CustomTarget<Bitmap>() {
            override fun onLoadCleared(placeholder: Drawable?) {
                image = null
                drawable = placeholder
            }

            override fun onResourceReady(
                bitmap: Bitmap,
                transition: Transition<in Bitmap>?
            ) {
                image = bitmap.asImageAsset()
            }
        }
        glide.asBitmap()
            .load(url)
            .into(target)

        onDispose {
            image = null
            drawable = null
            glide.clear(target)
        }
    }

    val theImage = image
    val theDrawable = drawable
    if (theImage != null) {
        Box(
            modifier = modifier,
            gravity = ContentGravity.Center
        ) {
            Image(asset = theImage)
        }
    } else if (theDrawable != null) {
        Canvas(modifier = modifier) {
            drawCanvas { canvas, _ ->
                theDrawable.draw(canvas.nativeCanvas)
            }
        }
    }
}

@Composable
fun AppDrawer(currentScreen: AppScreen, closeDrawer: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(Modifier.preferredHeight(24.dp))
        DrawerButton(
            icon = Filled.Home,
            label = "Home",
            isSelected = currentScreen == Screen1,
            action = {
                navigateTo(Screen1)
                closeDrawer()
            }
        )

        DrawerButton(
            icon = Filled.Favorite,
            label = "Ranking",
            isSelected = currentScreen == Screen2,
            action = {
                navigateTo(Screen2)
                closeDrawer()
            }
        )

        DrawerButton(
            icon = Filled.DateRange,
            label = "BooShelf",
            isSelected = currentScreen == Screen3,
            action = {
                navigateTo(Screen3)
                closeDrawer()
            }
        )

        DrawerButton(
            icon = Filled.Call,
            label = "Store",
            isSelected = currentScreen == Screen4,
            action = {
                navigateTo(Screen4)
                closeDrawer()
            }
        )

        DrawerButton(
            icon = Filled.Face,
            label = "MyPage",
            isSelected = currentScreen == Screen5,
            action = {
                navigateTo(Screen5)
                closeDrawer()
            }
        )

        DrawerButton(
            icon = Filled.ThumbUp,
            label = "Viewer",
            isSelected = currentScreen == Screen6,
            action = {
                navigateTo(Screen6)
                closeDrawer()
            }
        )
    }
}

@Composable
private fun DrawerButton(
    icon: VectorAsset,
    label: String,
    isSelected: Boolean,
    action: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colors
    val imageAlpha = if (isSelected) {
        1f
    } else {
        0.6f
    }
    val textIconColor = if (isSelected) {
        colors.primary
    } else {
        colors.onSurface.copy(alpha = 0.6f)
    }
    val backgroundColor = if (isSelected) {
        colors.primary.copy(alpha = 0.12f)
    } else {
        colors.surface
    }

    val surfaceModifier = modifier
        .padding(start = 8.dp, top = 8.dp, end = 8.dp)
        .fillMaxWidth()
    Surface(
        modifier = surfaceModifier,
        color = backgroundColor,
        shape = MaterialTheme.shapes.small
    ) {
        TextButton(
            onClick = action,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Start,
                verticalGravity = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    asset = icon,
                    colorFilter = ColorFilter.tint(textIconColor),
                    alpha = imageAlpha
                )
                Spacer(Modifier.preferredWidth(16.dp))
                Text(
                    text = label,
                    style = MaterialTheme.typography.body2,
                    color = textIconColor,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun BodyContentComponent(
    currentScreen: AppScreen,
    curlViewModel: CurlViewModel,
    homeViewModel: HomeViewModel
) {
    when (currentScreen) {
        Screen1 -> HomeViewComponent(homeViewModel)
        Screen2 -> RankingComponent()
        Screen3 -> BookShelfComponent()
        Screen4 -> StoreComponent()
        Screen5 -> MyPageComponent()
        Screen6 -> CurlViewComponent(curlViewModel)
    }
}

@Composable
fun BottomNavigationOnlySelectedLabelComponent() {
    val listItems = listOf("ホーム", "ランキング", "本棚", "ストア", "マイページ")
    BottomNavigation(
        modifier = Modifier.padding(0.dp)
    ) {
        listItems.forEachIndexed { index, label ->
            BottomNavigationItem(
                icon = {
                    Icon(asset = Filled.Favorite)
                },
                text = {
                    Text(
                        text = label,
                        style = TextStyle(
                            fontFamily = FontFamily.Monospace,
                            fontSize = 10.sp
                        )
                    )
                },
                selected = CurlViewStatus.selectIndex == index,
                onSelected = {
                    CurlViewStatus.selectIndex = index
                    navigateTo(getScreenBasedOnIndex(index))
                },
                alwaysShowLabels = false
            )
        }
    }
}

fun Modifier.RoundedCornerClipModifier(size: Dp): Modifier = composed {
    val shape = RoundedCornerShape(size)
    clip(shape)
}
