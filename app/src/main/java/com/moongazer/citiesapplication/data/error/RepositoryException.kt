package com.android.appname.data.error

data class RepositoryException(
    val code: Int,
    val errorBody: String?,
    val msg: String
) : RuntimeException(msg)
