package com.riz.mindbody.ui.province

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riz.mindbody.data.entities.Province
import com.riz.mindbody.data.repo.CountryProvinceRepo
import com.riz.mindbody.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProvinceViewModel @Inject constructor(private val countryProvinceRepo: CountryProvinceRepo) :
    ViewModel() {

    private var _provinceList: MutableLiveData<Resource<List<Province>>> = MutableLiveData()

    val provinceList: LiveData<Resource<List<Province>>> get() =  _provinceList


    fun getProvince(id: Int){
        viewModelScope.launch {
            val provinceRes = countryProvinceRepo.getProvinces(id)
            _provinceList.value = provinceRes
        }
    }

}