package com.moongazer.citiesapplication.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.loadingFlow
import androidx.lifecycle.viewErrorFlow
import com.moongazer.citiesapplication.arch.extensions.LoadingAware
import com.moongazer.citiesapplication.arch.extensions.ViewErrorAware
import com.moongazer.citiesapplication.arch.extensions.collectFlow
import com.moongazer.citiesapplication.ui.widgets.CustomProgressDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {
    open val viewModel: ViewModel? = null

    private val progressDialog by lazy {
        CustomProgressDialog.newInstance()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel?.run {
            if (this is ViewErrorAware) {
                collectFlow(viewErrorFlow) {
                    (activity as? BaseActivity)?.handleCommonError(it)
                }
            }

            if (this is LoadingAware) {
                collectFlow(loadingFlow) {
                    handleProgressDialogStatus(it)
                }
            }
        }
    }

    private fun handleProgressDialogStatus(isShow: Boolean) {
        if (isShow) {
            progressDialog.show(
                childFragmentManager,
                CustomProgressDialog::class.java.name
            )
        } else {
            progressDialog.dismissAllowingStateLoss()
        }
    }
}
