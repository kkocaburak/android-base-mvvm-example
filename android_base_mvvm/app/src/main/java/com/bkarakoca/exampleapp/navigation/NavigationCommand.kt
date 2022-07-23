package com.bkarakoca.exampleapp.navigation

import androidx.navigation.NavDirections
import com.bkarakoca.exampleapp.internal.popup.PopupListener
import com.bkarakoca.exampleapp.internal.popup.PopupModel

sealed class NavigationCommand {
    data class ToDirection(val directions: NavDirections) : NavigationCommand()
    data class Popup(val model: PopupModel, val listener: PopupListener? = null) :
        NavigationCommand()

    object Back : NavigationCommand()
}
