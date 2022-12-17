package com.mhdncb.testblurapp.utils

import com.mhdncb.testblurapp.R

sealed class Screen(val name: String, val route: String, val icon: Int?) {

    object Home: Screen(name = "Home", route = "Home", icon = R.drawable.home)

    object Profil: Screen(name = "Profil", route = "Profil", icon = R.drawable.user)

    object Post: Screen(name = "Post", route = "Post", icon = null)

}