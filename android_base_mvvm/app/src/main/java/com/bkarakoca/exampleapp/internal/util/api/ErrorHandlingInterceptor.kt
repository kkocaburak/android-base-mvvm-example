package com.bkarakoca.exampleapp.internal.util.api

import com.bkarakoca.exampleapp.R
import com.bkarakoca.exampleapp.internal.util.Failure
import com.bkarakoca.exampleapp.internal.util.NetworkStateHolder
import com.bkarakoca.exampleapp.internal.util.ResourceProvider
import java.io.IOException
import java.net.SocketTimeoutException
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.HttpException

class ErrorHandlingInterceptor(
    private val networkStateHolder: NetworkStateHolder,
    private val resourceProvider: ResourceProvider
) : Interceptor {

    @Throws(IOException::class, Failure::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!networkStateHolder.isConnected) {
            throw Failure.NoConnectivityError(resourceProvider.getString(R.string.common_error_network_connection))
        }

        val response = try {
            chain.proceed(chain.request())
        } catch (exception: Exception) {
            throw when (exception) {
                is HttpException -> Failure.ApiError(exception.code(), exception.message())
                is SocketTimeoutException -> Failure.TimeOutError(exception.message)
                else -> IOException(exception)
            }
        }

        if (response.isSuccessful) {
            if (response.body == null) {
                throw Failure.EmptyResponse
            }
            return response
        } else {
            val responseJson = response.body?.string()
                ?: throw Failure.ApiError(
                    code = response.code,
                    message = response.message
                )

            throw Failure.ApiError(
                code = 1,
                message = responseJson
            )
        }
    }
}
