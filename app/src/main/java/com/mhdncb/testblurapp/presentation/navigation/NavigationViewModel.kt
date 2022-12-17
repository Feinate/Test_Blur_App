package com.mhdncb.testblurapp.presentation.navigation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
) : ViewModel() {

    // BottomBar

    private val _bottomBarState = mutableStateOf(true)
    val bottomBarState: State<Boolean> = _bottomBarState

    fun setBottomBarValue(value: Boolean) {
        _bottomBarState.value = value
    }

}