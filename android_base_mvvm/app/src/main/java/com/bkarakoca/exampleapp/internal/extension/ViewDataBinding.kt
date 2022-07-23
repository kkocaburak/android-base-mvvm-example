package com.bkarakoca.exampleapp.internal.extension

import androidx.databinding.ViewDataBinding

fun <VB : ViewDataBinding> VB.executeAfter(block: VB.() -> Unit) {
    block.invoke(this)
    executePendingBindings()
}
