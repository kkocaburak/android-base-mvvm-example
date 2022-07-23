package com.bkarakoca.exampleapp.data.remote

import com.bkarakoca.exampleapp.internal.util.Failure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

open class BaseRemoteDataSource {

    suspend fun <O> invokeFlow(serviceFunction: suspend () -> O): Flow<O> = flow {
        try {
            emit(serviceFunction())
        } catch (exception: Exception) {
            throw asFailure(exception)
        }
    }

    private fun asFailure(exception: Exception): Failure {
        return when (exception) {
            is Failure -> exception
            else -> Failure.UnknownError(exception)
        }
    }
}