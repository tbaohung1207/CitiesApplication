package com.moongazer.citiesapplication.ui.test

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * @author ml-hungtruong
 */
class CreateCodeSmell : AppCompatActivity() {
    fun testWhenNested() {
        val a = "a"
        val x = 1
        when (a) {
            "a" -> "b"
            "c" -> "d"
            "e" -> "f"
            "g" -> when (x) {
                2 -> "f"
                1 -> "l"
                3 -> "g"
                4 -> "e"
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
    }

    fun test_function_naming() {

    }

    fun testPairsParentheses() {
        if ((true)) {
            println("das")
        }
        // FIXME: Fix test
    }
}
