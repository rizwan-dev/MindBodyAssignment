package com.riz.mindbody.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.riz.mindbody.data.remote.APIService
import com.riz.mindbody.data.remote.RemoteDataSource
import com.riz.mindbody.data.repo.CountryProvinceRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://connect.mindbodyonline.com/rest/worldregions/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideAPIService(retrofit: Retrofit): APIService = retrofit.create(APIService::class.java)

    @Singleton
    @Provides
    fun provideRemoteDataSource(apiService: APIService) = RemoteDataSource(apiService)

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: RemoteDataSource) = CountryProvinceRepo(remoteDataSource)
}