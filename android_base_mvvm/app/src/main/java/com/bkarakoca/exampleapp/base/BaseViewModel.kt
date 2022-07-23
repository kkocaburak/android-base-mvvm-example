package com.bkarakoca.exampleapp.base

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.bkarakoca.exampleapp.R
import com.bkarakoca.exampleapp.internal.popup.PopupListener
import com.bkarakoca.exampleapp.internal.popup.PopupModel
import com.bkarakoca.exampleapp.internal.util.Event
import com.bkarakoca.exampleapp.internal.util.Failure
import com.bkarakoca.exampleapp.internal.util.ResourceProvider
import com.bkarakoca.exampleapp.navigation.NavigationCommand
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    @Inject
    lateinit var resourceProvider: ResourceProvider

    private val _popup = MutableLiveData<Event<PopupModel>>()
    val popup: LiveData<Event<PopupModel>> get() = _popup

    private val _navigation = MutableLiveData<Event<NavigationCommand>>()
    val navigation: LiveData<Event<NavigationCommand>> get() = _navigation

    protected open fun showErrorPopupWithBackAction(message: String) {
        _navigation.value = Event(
            NavigationCommand.Popup(
                PopupModel(
                    message = message
                ),
                PopupListener(
                    onPositiveButtonClick = { navigateBack() }
                )
            )
        )
    }

    protected open fun showPopup(message: String) {
        _popup.value = Event(PopupModel(message = message))
    }

    protected open fun handleFailure(failure: Failure) {
        val message = when (failure) {
            is Failure.NoConnectivityError ->
                getString(R.string.common_error_network_connection)
            is Failure.ApiError,
            is Failure.CustomException ->
                failure.message
            is Failure.UnknownError ->
                failure.exception.localizedMessage
                    ?: getString(R.string.common_error_unknown)
            is Failure.TimeOutError ->
                getString(R.string.common_error_timeout)
            else ->
                failure.message ?: failure.toString()
        }

        _popup.value = Event(
            PopupModel(
                message = message
            )
        )
    }

    fun navigate(directions: NavDirections) {
        _navigation.value = Event(NavigationCommand.ToDirection(directions))
    }

    fun navigate(model: PopupModel, listener: PopupListener?) {
        _navigation.value = Event(NavigationCommand.Popup(model, listener))
    }

    fun navigateBack() {
        _navigation.value = Event(NavigationCommand.Back)
    }

    protected fun getString(@StringRes resId: Int): String {
        return resourceProvider.getString(resId)
    }

    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        handleFailure(Failure.CustomException(message = throwable.localizedMessage))
    }
}
