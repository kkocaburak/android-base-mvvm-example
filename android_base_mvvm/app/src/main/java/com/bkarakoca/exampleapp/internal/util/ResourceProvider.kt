package com.bkarakoca.exampleapp.internal.util

import android.content.Context
import androidx.annotation.StringRes

interface ResourceProvider {

    fun getString(@StringRes resId: Int, vararg formatArgs: Any): String

    fun getString(@StringRes resId: Int): String

    fun getAsset(path: String): String
}

class ResourceProviderImpl(val context: Context) : ResourceProvider {

    override fun getString(@StringRes resId: Int): String {
        return context.resources.getString(resId)
    }

    override fun getString(@StringRes resId: Int, vararg formatArgs: Any): String {
        return context.resources.getString(resId, *formatArgs)
    }

    override fun getAsset(path: String): String {
        return context.assets.open(path)
            .bufferedReader()
            .use { it.readText() }
    }
}
