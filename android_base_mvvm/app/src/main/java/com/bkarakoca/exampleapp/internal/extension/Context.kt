package com.bkarakoca.exampleapp.internal.extension

import android.content.Context
import android.widget.Toast
import com.bkarakoca.exampleapp.internal.popup.Popup
import com.bkarakoca.exampleapp.internal.popup.PopupListener
import com.bkarakoca.exampleapp.internal.popup.PopupModel

fun Context.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, duration).show()
}

fun Context.showPopup(model: PopupModel, listener: PopupListener? = null) {
    Popup(this, model, listener).show()
}
