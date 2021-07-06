package com.moongazer.citiesapplication.data.error

import java.net.HttpURLConnection


sealed class ErrorModel : Throwable() {
    open fun isCommonError(): Boolean = false

    companion object {
        const val API_ERROR_RESULT_CODE = "1"
    }

    sealed class Http : ErrorModel() {
        data class ApiError(
            val code: String?,
            override val message: String?,
            val apiUrl: String?
        ) : Http() {
            override fun isCommonError(): Boolean {
                if (code == HttpURLConnection.HTTP_UNAUTHORIZED.toString()
                    || code == HttpURLConnection.HTTP_INTERNAL_ERROR.toString()
                ) {
                    return true
                }
                return false
            }
        }
    }

    data class LocalError(val errorMessage: String, val code: String?) : ErrorModel()

    enum class LocalErrorException(val message: String, val code: String) {
        NO_INTERNET_EXCEPTION("No Internet", "1001"),
        REQUEST_TIME_OUT_EXCEPTION("Request timeout", "1002"),
        UNKNOWN_EXCEPTION("Unknown error", "1000")
    }
}
