package com.riz.mindbody.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: APIService) : BaseDataSource() {
    suspend fun getCountries() = getResult { apiService.getAllCountries() }
    suspend fun getProvinces(id: Int) = getResult { apiService.getAllProvinces(id) }
}