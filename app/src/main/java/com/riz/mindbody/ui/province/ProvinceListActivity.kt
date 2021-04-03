package com.riz.mindbody.ui.province

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.riz.mindbody.databinding.ActivityProvinceListBinding
import com.riz.mindbody.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ProvinceListActivity : AppCompatActivity() {

    lateinit var binding: ActivityProvinceListBinding

    private val viewModel: ProvinceViewModel by viewModels()

    private lateinit var adapter: ProvinceListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProvinceListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        setupLiveDataObserver()
    }

    private fun initView() {
        val countryId = intent.getIntExtra(COUNTRY_ID, -1)
        viewModel.getProvince(countryId)
        binding.progress.visibility = View.VISIBLE
        adapter = ProvinceListAdapter {

        }

        binding.rvProvinces.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))

        binding.rvProvinces.adapter = adapter
    }

    private fun setupLiveDataObserver() {
        viewModel.provinceList.observe(this, Observer {
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

    companion object {

        private const val COUNTRY_ID = "country_id"

        fun newInstance(context: Context, id: Int) =
            Intent(context, ProvinceListActivity::class.java).apply {
                putExtra(
                    COUNTRY_ID, id
                )
            }
    }
}