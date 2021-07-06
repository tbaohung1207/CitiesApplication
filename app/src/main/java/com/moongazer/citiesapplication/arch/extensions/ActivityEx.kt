package com.moongazer.citiesapplication.arch.extensions

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

internal fun AppCompatActivity.replaceFragment(
    @IdRes containerId: Int, fragment: Fragment,
    t: (transaction: FragmentTransaction) -> Unit = {},
    isAddBackStack: Boolean = false
) {
    if (supportFragmentManager.findFragmentByTag(fragment.javaClass.name) == null) {
        val transaction = supportFragmentManager.beginTransaction()
        t.invoke(transaction)
        transaction.replace(containerId, fragment, fragment.javaClass.name)
        if (isAddBackStack) {
            transaction.addToBackStack(fragment.javaClass.name)
        }
        transaction.commitAllowingStateLoss()
    }
}
