package com.bkarakoca.exampleapp.internal.extension

fun Double?.toSafeDouble(): Double {
    return this ?: 0.0
}