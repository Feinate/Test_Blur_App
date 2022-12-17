package com.mhdncb.testblurapp.presentation.components.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mhdncb.testblurapp.ui.theme.montserrat

@Composable
fun CategoryItem(
    name: String,
    color: Color
) {

    Card(
        modifier = androidx.compose.ui.Modifier
            .height(225.dp)
            .width(160.dp),
        colors = CardDefaults.cardColors(
            containerColor = color,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            0.dp, 0.dp, 0.dp, 0.dp, 0.dp, 0.dp
        )
    ) {
        Column(
            modifier = androidx.compose.ui.Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = name,
                fontFamily = montserrat,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp
            )
        }
    }

}