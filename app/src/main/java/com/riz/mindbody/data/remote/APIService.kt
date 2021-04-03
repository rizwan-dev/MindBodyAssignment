package com.riz.mindbody.data.remote

import com.riz.mindbody.data.entities.Country
import com.riz.mindbody.data.entities.Province
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {

    @GET("country")
    suspend fun getAllCountries() : Response<List<Country>>

    @GET("country/{id}/province")
    suspend fun getAllProvinces(@Path("id") id: Int): Response<List<Province>>

}