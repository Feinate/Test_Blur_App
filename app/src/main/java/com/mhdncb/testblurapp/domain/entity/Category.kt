package com.mhdncb.testblurapp.domain.entity

import androidx.compose.ui.graphics.Color
import com.mhdncb.testblurapp.ui.theme.DarkPurple
import com.mhdncb.testblurapp.ui.theme.LightSalmon
import com.mhdncb.testblurapp.ui.theme.VioletCrayola

class Category (

    val name: String = "",
    val color: Color = Color.White

)

object Categories {

    val categories = listOf(
        Category(
            name = "Productivity",
            color = LightSalmon
        ),
        Category(
            name = "Marketing",
            color = VioletCrayola
        ),
        Category(
            name = "Crypto",
            color = DarkPurple
        )
    )

}