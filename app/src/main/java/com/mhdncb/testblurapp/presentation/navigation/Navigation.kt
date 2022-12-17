package com.mhdncb.testblurapp.presentation.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mhdncb.testblurapp.presentation.screen.home.Home
import com.mhdncb.testblurapp.presentation.screen.post.Post
import com.mhdncb.testblurapp.utils.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(
    viewModel: NavigationViewModel = hiltViewModel()
) {

    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val destination = navBackStackEntry?.destination

    val currentRoute = destination?.route

    val bottomBarState = viewModel.bottomBarState

    when (currentRoute) {
        Screen.Home.route -> {
            viewModel.setBottomBarValue(true)
        }
        Screen.Post.route -> {
            viewModel.setBottomBarValue(false)
        }
    }

    Scaffold(
        containerColor = White,
//        floatingActionButton = {
//            AddButton(
//                isAppearing = makeButtonAppear,
//                bottomBarState = bottomBarState,
//                onAddPrayClick = {
//                    scope.launch {
//                        makeButtonAppear.value = false
//                        delay(100)
//                        navController.navigate(route = Screen.AddPray.route) {
//                            launchSingleTop = true
//                        }
//                    }
//                },
//                onAddWasherClick = {
//                    Toast.makeText(context, "Fonctionnalité non disponible pour le moment.", Toast.LENGTH_SHORT).show()
////                    makeButtonAppear.value = false
////                    navController.navigate(route = Screen.AddWasher.route) {
////                        popUpTo(Screen.Home.route)
////                        launchSingleTop = true
////                    }
//                },
//                onClickAdd = {
//                    makeButtonAppear.value = !makeButtonAppear.value
//                }
//            )
//        },
//        isFloatingActionButtonDocked = false,
//        floatingActionButtonPosition = FabPosition.End,
        bottomBar = {
            BottomAppNavigation(
                navController = navController,
                bottomBarState = bottomBarState,
                currentRoute = currentRoute,
                destination = destination
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            ScreenController(
                paddingValues = it,
                navController = navController,
            )
        }
    }
}

@Composable
fun BottomAppNavigation(
    navController: NavController,
    bottomBarState: State<Boolean>,
    currentRoute: String?,
    destination: NavDestination?
) {
    val items = listOf(Screen.Home, Screen.Profil)

    AnimatedVisibility(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp), // .clip(RoundedCornerShape(18.dp)) ,
            //.shadow(elevation = 5.dp, clip = true, shape = RoundedCornerShape(16.dp)),
        visible = bottomBarState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {
        NavigationBar(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .navigationBarsPadding()
                .clip(RoundedCornerShape(16.dp)),
            containerColor = White.copy(alpha = 0.7f),
            tonalElevation = 0.dp
        ) {
            items.forEach { screen ->
                NavigationBarItem(
                    interactionSource = MutableInteractionSource(),
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = White,
                        selectedIconColor = Black,
                        unselectedIconColor = LightGray,
                    ),
                    alwaysShowLabel = false,
                    icon = {
                        screen.icon?.let { icon ->
                            Icon(
                                painter = painterResource(id = icon),
                                contentDescription = screen.icon.toString(),
                                modifier = Modifier.size(27.dp)
                            )
                        }
                    },
                    selected = destination?.hierarchy?.any { it.route == screen.route } == true,
                    onClick = {
                        if (currentRoute != screen.route) {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = false
                                }
                                launchSingleTop = true
                                restoreState = false
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun ScreenController(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(
            route = Screen.Home.route
        ) {
            Home {
                navController.navigate(
                    Screen.Post.route
                )
            }
        }

        composable(
            route = Screen.Post.route
        ) {
            Post {
                navController.popBackStack()
            }
        }
    }
}