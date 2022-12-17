package com.mhdncb.testblurapp.presentation.screen.home

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mhdncb.testblurapp.domain.entity.Categories
import com.mhdncb.testblurapp.presentation.components.home.CategoryItem
import com.mhdncb.testblurapp.ui.theme.Blush
import com.mhdncb.testblurapp.ui.theme.PeachPuff
import com.mhdncb.testblurapp.ui.theme.montserrat
import kotlinx.coroutines.delay

@Composable
fun Home(
    toPost: () -> Unit
) {

    val categories = Categories.categories

    val isEnd = remember(Boolean) {
        mutableStateOf(false)
    }

    val redCircle = remember {
        mutableStateOf(IntOffset(x = (-400), y = (1000)))
    }

    val peachCircle = remember {
        mutableStateOf(IntOffset(x = (-700), y = (-1000)))
    }

    val homeScale = remember {
        mutableStateOf(0.85f)
    }

    val homeAlpha = remember {
        mutableStateOf(0.4f)
    }

    val animatedRedCircle by animateIntOffsetAsState(
        redCircle.value,
        tween(
            durationMillis = 1500
        )
    )

    val animatedPeachCircle by animateIntOffsetAsState(
        peachCircle.value,
        tween(
            durationMillis = 1500
        )
    )

    val animatedHomeScale by animateFloatAsState(
        homeScale.value,
        tween(durationMillis = 1500)
    )

    val animatedHomeAlpha by animateFloatAsState(
        homeAlpha.value,
        tween(durationMillis = 1500)
    )

    LaunchedEffect(isEnd.value) {
        if (!isEnd.value) {
            redCircle.value = IntOffset(x = (-75), y = (250))
            peachCircle.value = IntOffset(x = (-205), y = (-205))
            homeScale.value = 1f
            homeAlpha.value = 1f
        } else {
            redCircle.value = IntOffset(x = (-400), y = (1000))
            peachCircle.value = IntOffset(x = (-700), y = (-1000))
            homeScale.value = 0.85f
            homeAlpha.value = 0.4f
            delay(400).apply {
                toPost()
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .blur(radius = 100.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
                .size(400.dp)
                .offset { animatedPeachCircle }
                .background(color = PeachPuff.copy(alpha = 0.9f), shape = CircleShape)
                .align(Alignment.TopStart)
        )
        Box(
            modifier = Modifier
                .blur(radius = 75.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
                .size(200.dp)
                .offset { animatedRedCircle }
                .background(color = Blush.copy(alpha = 0.9f), shape = CircleShape)
                .align(Alignment.BottomStart)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .scale(animatedHomeScale)
                .alpha(animatedHomeAlpha),
            contentPadding = PaddingValues(top = 8.dp),
        ) {
            item {
                Text(
                    modifier = Modifier
                        .padding(top = 20.dp, start = 16.dp),
                    text = "Pick the\nCategories",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 40.sp,
                    color = Color.Black,
                    fontFamily = montserrat,
                    lineHeight = 46.sp
                )
                Spacer(modifier = Modifier.height(30.dp))
            }
            item {
                LazyRow(
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(categories) {
                        CategoryItem(
                            name = it.name,
                            color = it.color
                        )
                    }
                }
            }
            item {
                Divider(
                    modifier = Modifier
                        .padding(horizontal = 125.dp, vertical = 20.dp)
                        .clip(
                            RoundedCornerShape(8.dp)
                        ),
                    color = Color.LightGray.copy(alpha = 0.4f)
                )
            }
            item {
                Text(
                    modifier = Modifier
                        .padding(top = 10.dp, start = 16.dp),
                    text = "Latest post",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 28.sp,
                    color = Color.Black,
                    fontFamily = montserrat
                )
                Spacer(modifier = Modifier.height(30.dp))
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .clickable {
                            isEnd.value = true
                        }
                ) {
                    Image(
                        modifier = Modifier
                            .weight(3f)
                            .clip(RoundedCornerShape(8.dp)),
                        painter = painterResource(id = com.mhdncb.testblurapp.R.drawable.marketing),
                        contentScale = ContentScale.Crop,
                        contentDescription = "Post"
                    )
                    Column(
                        modifier = Modifier
                            .weight(4f)
                            .padding(horizontal = 30.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("Stunning power of Triggers in Marketing", fontFamily = montserrat, fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                    }
                }
            }
        }
    }
}