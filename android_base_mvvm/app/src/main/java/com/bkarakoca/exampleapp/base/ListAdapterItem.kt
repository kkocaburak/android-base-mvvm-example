package com.bkarakoca.exampleapp.base

interface ListAdapterItem {
    val id: Long?
    val layoutId: Int

    override fun equals(other: Any?): Boolean
}
