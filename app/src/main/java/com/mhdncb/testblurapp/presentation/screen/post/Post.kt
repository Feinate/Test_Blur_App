package com.mhdncb.testblurapp.presentation.screen.post

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
import com.mhdncb.testblurapp.R
import com.mhdncb.testblurapp.domain.entity.Categories.categories
import com.mhdncb.testblurapp.presentation.components.home.CategoryItem
import com.mhdncb.testblurapp.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun Post(
    onBack: () -> Unit
) {

    val isEnd = remember(Boolean) {
        mutableStateOf(false)
    }

    val animatedSalmonCircle by animateIntOffsetAsState(
        targetValue = if (isEnd.value) IntOffset(x = (400), y = (550)) else IntOffset(x = (900), y = (1000)),
        animationSpec = tween(
            durationMillis = 1500
        )
    )

    val animatedVioletCircle by animateIntOffsetAsState(
        targetValue = if (isEnd.value) IntOffset(x = (-205), y = (-205)) else IntOffset(x = (700), y = (-1000)),
        animationSpec = tween(
            durationMillis = 1500
        )
    )

    val animatedPostScale by animateFloatAsState(
        targetValue = if (isEnd.value) 1f else 0.85f,
        animationSpec = tween(
            durationMillis = 1500
        )
    )

    LaunchedEffect(Unit) {
        if (!isEnd.value)
            isEnd.value = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .blur(radius = 100.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
                .size(400.dp)
                .offset { animatedSalmonCircle }
                .background(color = LightSalmon.copy(alpha = 0.9f), shape = CircleShape)
                .align(Alignment.BottomEnd)
        )
        Box(
            modifier = Modifier
                .blur(radius = 75.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
                .size(200.dp)
                .offset { animatedVioletCircle }
                .background(color = VioletCrayola.copy(alpha = 0.9f), shape = CircleShape)
                .align(Alignment.TopStart)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .scale(animatedPostScale)
                .alpha(animatedPostScale),
            contentPadding = PaddingValues(top = 8.dp),
        ) {
            item {
                Column {
                    Row(
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        IconButton(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(
                                    shape = CircleShape,
                                    color = Color.LightGray.copy(alpha = 0.4f)
                                ),
                            onClick = {
                                onBack()
                                isEnd.value = true
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.ArrowBack,
                                contentDescription = "Arrow Back",
                                tint = Color.Black
                            )
                        }
                    }
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

            }
            item {
                Column(
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
                            .fillMaxWidth()
                            .height(300.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        painter = painterResource(id = R.drawable.marketing),
                        contentScale = ContentScale.Crop,
                        contentDescription = "Post"
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("Stunning power of Triggers in Marketing, Lorem ipsum dolor sit amet consectetur.", fontFamily = montserrat, fontWeight = FontWeight.Medium, fontSize = 22.sp)
                    }
                }
            }
        }
    }

}