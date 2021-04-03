package com.riz.mindbody.ui.country.view

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.riz.mindbody.databinding.ActivityCountryListBinding
import com.riz.mindbody.ui.country.vm.CountryViewModel
import com.riz.mindbody.ui.province.ProvinceListActivity
import com.riz.mindbody.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class CountryListActivity : AppCompatActivity() {

    lateinit var binding: ActivityCountryListBinding

    private val viewModel: CountryViewModel by viewModels()

    private lateinit var adapter: CountryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountryListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupLiveDataObserver()
        setupAdapter()
    }

    private fun setupAdapter() {

        adapter = CountryListAdapter {
            startActivity(ProvinceListActivity.newInstance(this, it.ID))
        }

        binding.rvCountries.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))

        binding.rvCountries.adapter = adapter

    }

    private fun setupLiveDataObserver() {
        viewModel.getCountries()
        binding.progress.visibility = View.VISIBLE
        viewModel.countryList.observe(this, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progress.visibility = View.GONE
                    if (it.data.isNullOrEmpty()) {
                        binding.noData.visibility = View.VISIBLE
                    } else {
                        adapter.submitList(it.data)
                    }
                }
                Resource.Status.ERROR -> {
                    binding.progress.visibility = View.GONE
                    Timber.e(it.message)
                }
                Resource.Status.LOADING -> {
                    binding.progress.visibility = View.VISIBLE
                }
            }
        })
    }
}