package com.example.newsfeatcherdz

import androidx.room.Room
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://newsapi.org/"
private const val APP_DATABASE = "APP_DATABASE"



val networkModule = module {
    single<HttpLoggingInterceptor> {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)  }

    single<OkHttpClient> {
        OkHttpClient.Builder().addInterceptor(get<HttpLoggingInterceptor>()).build() }

    single<Retrofit> {  Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(get())
        .build() }


}

val databaseModule = module {
    single {
        Room
            .databaseBuilder(androidContext(),AppDataBase::class.java, APP_DATABASE)
            .fallbackToDestructiveMigration()
            .build()
    }
    single {
        get<AppDataBase>().bookmarksDao()
    }
}