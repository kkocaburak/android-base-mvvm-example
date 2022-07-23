package com.bkarakoca.exampleapp.internal.popup

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class PopupModel(
    var title: String? = null,
    var message: String? = null,
    var cancelable: Boolean = false,
    var positiveButtonText: String? = null,
    var negativeButtonText: String? = null
) : Parcelable
