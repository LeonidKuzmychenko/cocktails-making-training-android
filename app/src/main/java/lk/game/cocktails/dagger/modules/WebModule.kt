package lk.game.cocktails.dagger.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import lk.game.cocktails.retrofit.Api
import lk.game.cocktails.retrofit.repository.ApiRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
class WebModule {

    @Provides
    @Singleton
    fun provideRetrofit(@Named("Locale") locale: String): Retrofit {
        val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
        val headerLocale = Interceptor {
            val request = it.request().newBuilder().addHeader("locale", locale).build()
            it.proceed(request)
        }
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logger)
            .addInterceptor(headerLocale)
            .build()
        return Retrofit.Builder()
            .baseUrl("http://cocktails-making-training.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideApiRepository(api: Api): ApiRepository {
        return ApiRepository(api)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

}