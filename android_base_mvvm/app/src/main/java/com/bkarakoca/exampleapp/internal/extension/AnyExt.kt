package com.bkarakoca.exampleapp.internal.extension

fun Any?.toSafeString(): String {
    return this?.toString() ?: ""
}