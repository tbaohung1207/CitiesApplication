package com.android.appname.arch.extensions

import com.android.appname.data.error.RepositoryException
import com.moongazer.citiesapplication.data.error.ErrorModel
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

inline fun <T, R> Response<T>.mapSuccess(
    crossinline block: (T) -> R
): R {
    val safeBody = body()
    if (this.isSuccessful && safeBody != null) {
        return block(safeBody)
    } else {
        throw toError()
    }
}

fun <T> Response<T>.mapToRepositoryException(): RepositoryException {
    return RepositoryException(
        code = code(),
        errorBody = errorBody()?.string(),
        msg = message()
    )
}

fun <T> Response<T>.toError(): ErrorModel.Http {
    return try {
        ErrorModel.Http.ApiError(
            code = code().toString(),
            message = "",
            apiUrl = this.raw().request.url.toString()
        )
    } catch (ex: Exception) {
        ErrorModel.Http.ApiError(
            code = code().toString(),
            message = ErrorModel.LocalErrorException.UNKNOWN_EXCEPTION.message,
            apiUrl = this.raw().request.url.toString()
        )
    }
}

fun Throwable.toError(): ErrorModel {
    return when (this) {
        is SocketTimeoutException -> ErrorModel.LocalError(
            ErrorModel.LocalErrorException.REQUEST_TIME_OUT_EXCEPTION.message,
            ErrorModel.LocalErrorException.REQUEST_TIME_OUT_EXCEPTION.code
        )
        is UnknownHostException -> ErrorModel.LocalError(
            ErrorModel.LocalErrorException.NO_INTERNET_EXCEPTION.message,
            ErrorModel.LocalErrorException.NO_INTERNET_EXCEPTION.code
        )
        is ConnectException -> ErrorModel.LocalError(
            ErrorModel.LocalErrorException.NO_INTERNET_EXCEPTION.message,
            ErrorModel.LocalErrorException.NO_INTERNET_EXCEPTION.code
        )
        else -> ErrorModel.LocalError(this.message.toString(), "1014")
    }
}
