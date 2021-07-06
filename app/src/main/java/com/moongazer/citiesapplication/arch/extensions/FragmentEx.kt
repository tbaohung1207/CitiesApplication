package com.moongazer.citiesapplication.arch.extensions

import android.content.Context
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.moongazer.citiesapplication.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

internal fun Context.showErrorAlert(
    message: String,
    @StringRes buttonTitleRes: Int = R.string.ok,
    onOkClicked: () -> Unit = {}
) {
    val builder = AlertDialog.Builder(this)
    builder.setMessage(message)
    builder.setCancelable(false)
    builder.setNegativeButton(getString(buttonTitleRes)) { dialog, _ ->
        dialog.dismiss()
        onOkClicked.invoke()
    }
    builder.create().show()
}

fun Fragment.visibilityFlow(targetFlow: Flow<Boolean>, vararg view: View) {
    collectFlow(targetFlow) { loading ->
        view.forEach { it.isVisible = loading }
    }
}

fun <T> Fragment.collectFlow(targetFlow: Flow<T>, collectBlock: ((T) -> Unit)) {
    safeViewCollect {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            targetFlow.collect {
                collectBlock.invoke(it)
            }
        }
    }
}

private inline fun Fragment.safeViewCollect(crossinline viewOwner: LifecycleOwner.() -> Unit) {
    lifecycle.addObserver(object : DefaultLifecycleObserver {
        override fun onCreate(owner: LifecycleOwner) {
            viewLifecycleOwnerLiveData.observe(
                this@safeViewCollect,
                { viewLifecycleOwner ->
                    viewLifecycleOwner.viewOwner()
                }
            )
        }
    })
}