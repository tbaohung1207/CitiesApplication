package com.moongazer.citiesapplication.ui.main

import com.moongazer.citiesapplication.arch.extensions.viewBinding
import com.moongazer.citiesapplication.R
import com.moongazer.citiesapplication.arch.extensions.replaceFragment
import com.moongazer.citiesapplication.databinding.ActivityMainBinding
import com.moongazer.citiesapplication.ui.base.BaseActivity
import com.moongazer.citiesapplication.ui.cities.CitiesFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity(R.layout.activity_main) {
    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun afterViewCreated() {
        replaceFragment(R.id.flParent, CitiesFragment.newInstance())
    }
}
