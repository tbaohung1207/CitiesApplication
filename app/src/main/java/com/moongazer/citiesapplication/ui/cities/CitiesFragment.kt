package com.moongazer.citiesapplication.ui.cities

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.appname.arch.extensions.viewBinding
import com.moongazer.citiesapplication.R
import com.moongazer.citiesapplication.arch.extensions.collectFlow
import com.moongazer.citiesapplication.databinding.FragmentCitiesBinding
import com.moongazer.citiesapplication.ui.base.BaseFragment
import kotlinx.coroutines.flow.collect

class CitiesFragment : BaseFragment(R.layout.fragment_cities) {
    companion object {
        /**
         * Create new instance for [CitiesFragment]
         */
        fun newInstance() = CitiesFragment()
    }

    override val viewModel by viewModels<CitiesViewModel>()
    private val binding by viewBinding(FragmentCitiesBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        fetchData()
    }

    private fun initViews() {
        binding.recyclerView.run {
            adapter = CityAdapter(viewModel.getCities())
            layoutManager = LinearLayoutManager(context)
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.clearCities()
            viewModel.fetchCities()
        }
    }

    private fun fetchData() {
        collectFlow(viewModel.fetchCities()) {
            binding.recyclerView.adapter?.notifyDataSetChanged()
        }
    }
}