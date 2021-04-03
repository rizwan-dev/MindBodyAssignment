package com.riz.mindbody.ui.country.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riz.mindbody.data.entities.Country
import com.riz.mindbody.data.repo.CountryProvinceRepo
import com.riz.mindbody.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(private val countryProvinceRepo: CountryProvinceRepo) :
    ViewModel() {

    private var _countryList: MutableLiveData<Resource<List<Country>>> = MutableLiveData()

    val countryList: LiveData<Resource<List<Country>>> get() =  _countryList


    fun getCountries(){
        viewModelScope.launch {
            val countriesRes = countryProvinceRepo.getCountries()
            _countryList.value = countriesRes
        }
    }



}