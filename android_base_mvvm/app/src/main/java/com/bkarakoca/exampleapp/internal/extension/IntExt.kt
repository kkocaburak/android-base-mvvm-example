package com.bkarakoca.exampleapp.internal.extension

fun Int?.toSafeInt(): Int {
    return this ?: 0
}