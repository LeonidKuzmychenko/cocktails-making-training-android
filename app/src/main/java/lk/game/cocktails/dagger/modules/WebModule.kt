package lk.game.cocktails.dagger.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import lk.game.cocktails.dagger.annotation.named.Keys
import lk.game.cocktails.dagger.annotation.named.Qualifier
import lk.game.cocktails.retrofit.Api
import lk.game.cocktails.retrofit.converters.NullOnEmptyConverterFactory
import lk.game.cocktails.retrofit.repository.ApiRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class WebModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        @Qualifier(Keys.SERVER_NAME) host: String,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(host)
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        logger: HttpLoggingInterceptor,
        headerLocale: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .addInterceptor(headerLocale)
            .build()
    }

    @Provides
    @Singleton
    fun provideHeaderInterceptor(@Qualifier(Keys.LOCALE) locale: String): Interceptor {
        return Interceptor {
            val request = it.request().newBuilder().addHeader("locale", locale).build()
            it.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
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