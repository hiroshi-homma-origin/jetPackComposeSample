package com.kotlin.pagecurl.presentation.home

import androidx.compose.Composable
import androidx.compose.getValue
import androidx.ui.core.Modifier
import androidx.ui.core.boundsInParent
import androidx.ui.core.boundsInRoot
import androidx.ui.core.onChildPositioned
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.ScrollerPosition
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.ColumnScope.weight
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.height
import androidx.ui.layout.padding
import androidx.ui.layout.preferredHeight
import androidx.ui.layout.width
import androidx.ui.material.Card
import androidx.ui.material.ListItem
import androidx.ui.material.Surface
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontFamily
import androidx.ui.text.font.FontWeight
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.kotlin.pagecurl.api.fragment.Pokemon
import com.kotlin.pagecurl.domainobject.model.colors
import com.kotlin.pagecurl.domainobject.state.CurlViewStatus
import com.kotlin.pagecurl.domainobject.state.CurlViewStatus.pokeListSize
import com.kotlin.pagecurl.domainobject.state.setScrollHomeOffset
import com.kotlin.pagecurl.presentation.common.GlideImage
import com.kotlin.pagecurl.presentation.common.LiveDataLoadingComponent
import com.kotlin.pagecurl.viewModel.HomeViewModel
import timber.log.Timber

@Composable
fun HomeViewComponent(
    homeViewModel: HomeViewModel
) {
//    val pList by homeViewModel.getData().observeAsState(initial = emptyList())
    LiveDataComponent(homeViewModel.getData().value!!, homeViewModel)
}

@Composable
fun LiveDataComponent(pokeList: List<Pokemon>, homeViewModel: HomeViewModel) {
    if (pokeList.isNotEmpty()) {
        LiveDataComponentList(pokeList, homeViewModel)
    } else {
        LiveDataLoadingComponent()
    }
}

@Composable
fun LiveDataComponentList(
    pList: List<Pokemon>,
    homeViewModel: HomeViewModel
) {
    Surface(modifier = Modifier.weight(1f)) {
        Box(
            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 55.dp), gravity = ContentGravity.Center,
            children = {
                ChildrenCompose(pList, homeViewModel)
            }
        )
    }
}

@Composable
fun ChildrenCompose(pList: List<Pokemon>, homeViewModel: HomeViewModel) {
    var checkCount = 0
    VerticalScroller(
        modifier = Modifier.onChildPositioned {
            Timber.d("check_condition1:${it.boundsInParent.bottom}")
            Timber.d("check_condition2:${it.boundsInRoot.bottom}")
            if (it.boundsInParent.bottom < it.boundsInRoot.bottom) {
                checkCount++
                if (checkCount == 1) {
                    if (pokeListSize < 152) pokeListSize += 20
                    Timber.d("check_condition3:$pokeListSize")
                }
            }
            setScrollHomeOffset(it)
        },
        scrollerPosition = ScrollerPosition(CurlViewStatus.offsetYHome)
    ) {
        Column {
            pList.mapIndexed { index, pokemon ->
                Card(
                    color = colors[index % colors.size],
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier.padding(4.dp) +
                        Modifier.fillMaxWidth() +
                        Modifier.preferredHeight(100.dp)
                ) {
                    ListItem(
                        text = {
                            Text(
                                text = "${index + 1}:${pokemon.name()}",
                                style = TextStyle(
                                    fontFamily = FontFamily.Serif, fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        },
                        secondaryText = {
                            Text(
                                text = "type: ${pokemon.types()}",
                                style = TextStyle(
                                    fontFamily = FontFamily.Serif, fontSize = 15.sp,
                                    fontWeight = FontWeight.Light, color = Color.DarkGray
                                )
                            )
                        },
                        icon = {
                            Timber.d("checkString:$pokemon.image()")
                            Surface(
                                color = Color(0xFFFFFFFF.toInt()),
                                modifier = Modifier.width(80.dp) + Modifier.height(80.dp)
                            ) {
                                GlideImage(pokemon.image().toString()) {
                                    fitCenter()
//                                    placeholder(R.drawable.placeholder)
//                                    diskCacheStrategy(DiskCacheStrategy.ALL)
//                                    onlyRetrieveFromCache(true)
//                                    transition(withCrossFade(10000))
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}
