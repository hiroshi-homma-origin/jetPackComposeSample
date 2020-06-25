package com.kotlin.pagecurl.presentation.common

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Handler
import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.onCommit
import androidx.compose.setValue
import androidx.compose.state
import androidx.ui.core.Alignment
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.Canvas
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
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
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontFamily
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.koduok.compose.navigation.Router
import com.koduok.compose.navigation.core.BackStack
import com.kotlin.pagecurl.domainobject.model.AppRoute
import com.kotlin.pagecurl.domainobject.model.AppRoute.HomeRoute
import com.kotlin.pagecurl.domainobject.model.AppRoute.Tab1Route
import com.kotlin.pagecurl.domainobject.model.AppRoute.Tab2Route
import com.kotlin.pagecurl.domainobject.model.AppRoute.Tab3Route
import com.kotlin.pagecurl.domainobject.model.AppRoute.Tab4Route
import com.kotlin.pagecurl.domainobject.model.AppRoute.Tab5Route
import com.kotlin.pagecurl.domainobject.state.CurlViewStatus
import com.kotlin.pagecurl.presentation.bookshelf.BookShelfComponent
import com.kotlin.pagecurl.presentation.curlViewer.CurlViewComponent
import com.kotlin.pagecurl.presentation.curlViewer.CurlViewModel
import com.kotlin.pagecurl.presentation.home.HomeViewComponent
import com.kotlin.pagecurl.presentation.home.HomeViewModel
import com.kotlin.pagecurl.presentation.mypage.MyPageComponent
import com.kotlin.pagecurl.presentation.ranking.RankingComponent
import com.kotlin.pagecurl.presentation.store.StoreComponent
import timber.log.Timber

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
fun AppDrawer(
    closeDrawer: () -> Unit,
    backStack: BackStack<AppRoute>
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(Modifier.preferredHeight(24.dp))
        DrawerButton(
            icon = Filled.Home,
            label = "Home",
            isSelected = CurlViewStatus.selectIndex == 0,
            action = {
                CurlViewStatus.selectIndex = 0
                CurlViewStatus.stack.add(0)
                backStack.push(HomeRoute)
                closeDrawer()
            }
        )

        DrawerButton(
            icon = Filled.Favorite,
            label = "Ranking",
            isSelected = CurlViewStatus.selectIndex == 1,
            action = {
                CurlViewStatus.selectIndex = 1
                CurlViewStatus.stack.add(1)
                backStack.push(Tab1Route)
                closeDrawer()
            }
        )

        DrawerButton(
            icon = Filled.DateRange,
            label = "BooShelf",
            isSelected = CurlViewStatus.selectIndex == 2,
            action = {
                CurlViewStatus.selectIndex = 2
                CurlViewStatus.stack.add(2)
                backStack.push(Tab2Route)
                closeDrawer()
            }
        )

        DrawerButton(
            icon = Filled.Call,
            label = "Store",
            isSelected = CurlViewStatus.selectIndex == 3,
            action = {
                CurlViewStatus.selectIndex = 3
                CurlViewStatus.stack.add(3)
                backStack.push(Tab3Route)
                closeDrawer()
            }
        )

        DrawerButton(
            icon = Filled.Face,
            label = "MyPage",
            isSelected = CurlViewStatus.selectIndex == 4,
            action = {
                CurlViewStatus.selectIndex = 4
                CurlViewStatus.stack.add(4)
                backStack.push(Tab4Route)
                closeDrawer()
            }
        )

        DrawerButton(
            icon = Filled.ThumbUp,
            label = "Viewer",
            isSelected = CurlViewStatus.selectIndex == 5,
            action = {
                CurlViewStatus.selectIndex = 5
                CurlViewStatus.stack.add(5)
                backStack.push(Tab5Route)
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
    val imageAlpha = if (isSelected) { 1f } else { 0.6f }
    val textIconColor = if (isSelected) { colors.primary } else { colors.onSurface.copy(alpha = 0.6f) }
    val backgroundColor = if (isSelected) { colors.primary.copy(alpha = 0.12f) } else { colors.surface }

    val surfaceModifier = modifier.padding(start = 8.dp, top = 8.dp, end = 8.dp)
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
    curlViewModel: CurlViewModel,
    homeViewModel: HomeViewModel
) {
    Router<AppRoute>(start = HomeRoute) {
        when (it.data) {
            HomeRoute -> HomeViewComponent(homeViewModel, this@Router)
            Tab1Route -> RankingComponent(this@Router)
            Tab2Route -> BookShelfComponent(this@Router)
            Tab3Route -> StoreComponent(this@Router)
            Tab4Route -> MyPageComponent(this@Router)
            Tab5Route -> CurlViewComponent(curlViewModel, this@Router)
        }
    }
}

@Composable
fun BottomNavigationOnlySelectedLabelComponent(
    backStack: BackStack<AppRoute>
) {
    val listItems = listOf("ホーム", "ランキング", "本棚", "ストア", "マイページ")
    val animationDelay = 215L
    Timber.d("check_currentRoute1:${backStack.current.data}")
    Timber.d("check_currentRoute2:${backStack.snapshot.last().data}")
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
                    CurlViewStatus.stack.add(index)
                    Handler().postDelayed(
                        {
                            when (index) {
                                0 -> backStack.push(HomeRoute)
                                1 -> backStack.push(Tab1Route)
                                2 -> backStack.push(Tab2Route)
                                3 -> backStack.push(Tab3Route)
                                4 -> backStack.push(Tab4Route)
                                5 -> backStack.push(Tab5Route)
                            }
                        },
                        animationDelay
                    )
                },
                alwaysShowLabels = false
            )
        }
    }
}
