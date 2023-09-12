package com.example.moviecompose.data.api



import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitFactory {
    fun getBuilder(
    ): Retrofit {
        val client = OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
            // Request
            val requestBuilder = chain.request().newBuilder()
            requestBuilder.addHeader("AUTHORIZATION", "Bearer $API_TOKEN")
            val response = chain.proceed(requestBuilder.build())
            response
        })
//
//        if (BuildConfig.DEBUG) {
//            val logging = HttpLoggingInterceptor()
//            logging.level = HttpLoggingInterceptor.Level.BODY
//            client.addInterceptor(logging)
//        }

        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        return Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL)
            .client(client.build()).build()
    }
}

const val BASE_URL = "https://api.themoviedb.org/3/"
const val API_TOKEN =
    "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3MzJjZjJkODNhNjdhZjY1MDZjMjBjMzIxNGViZjgyNyIsInN1YiI6IjY0ZWY4NjQ0Y2FhNTA4MDEyYjA1Y2E1NCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Yx3Rz_WnobciXDr0i8YE5YEqR5Nrlggf8yf2Npapqzg"