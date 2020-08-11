package com.kotlin.pagecurl.presentation.home

import androidx.compose.foundation.Box
import androidx.compose.foundation.ContentGravity
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope.weight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ListItem
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotlin.pagecurl.api.fragment.Pokemon
import com.kotlin.pagecurl.domainobject.model.colors
import com.kotlin.pagecurl.presentation.common.GlideImage
import com.kotlin.pagecurl.presentation.common.LiveDataLoadingComponent
import com.kotlin.pagecurl.viewModel.HomeViewModel
import timber.log.Timber

@Composable
fun HomeViewComponent(
    homeViewModel: HomeViewModel
) {
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
    ScrollableColumn() {
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
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}
