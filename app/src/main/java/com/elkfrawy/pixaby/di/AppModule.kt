package com.elkfrawy.pixaby.di

import com.elkfrawy.pixaby.data.remote.Api
import com.elkfrawy.pixaby.data.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okhttp = OkHttpClient().newBuilder().addInterceptor {
            // to add query to every request (Key)
            val request = it.request()
            val url = request.url
            val httpUrl =
                url.newBuilder().addQueryParameter("key", "39086290-63e25c487e73bef1565c48c50").build()
            val rb = request.newBuilder().url(httpUrl)
            it.proceed(rb.build())

         }.addInterceptor(loggingInterceptor).build()

        return okhttp
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()


    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit) = retrofit.create(Api::class.java)


}