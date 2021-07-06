package com.moongazer.citiesapplication.ui.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.moongazer.citiesapplication.arch.extensions.showErrorAlert
import com.moongazer.citiesapplication.data.error.ErrorModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseActivity(@LayoutRes layoutId: Int) : AppCompatActivity(layoutId) {
    abstract fun afterViewCreated()

    internal fun handleCommonError(errorModel: ErrorModel) {
        showErrorAlert(
            errorModel.message ?: ErrorModel.LocalErrorException.UNKNOWN_EXCEPTION.message
        )
    }
}
