package com.riz.mindbody.data.repo

import com.riz.mindbody.data.remote.RemoteDataSource
import javax.inject.Inject

class CountryProvinceRepo @Inject constructor(private val remoteDataSource: RemoteDataSource) {

    suspend fun getCountries() = remoteDataSource.getCountries()

    suspend fun getProvinces(id: Int) = remoteDataSource.getProvinces(id)

}