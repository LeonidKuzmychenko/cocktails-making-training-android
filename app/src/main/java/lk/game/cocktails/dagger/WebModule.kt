package lk.game.cocktails.dagger

import dagger.Module
import dagger.Provides
import lk.game.cocktails.retrofit.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class WebModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.43.207/myapi/public/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @Named("api1")
    fun provideApi1(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Provides
    @Singleton
    @Named("api2")
    fun provideApi2(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Provides
    @Singleton
    fun newObject(retrofit: Retrofit): Object {
        return Object()
    }

//    @Provides
//    @Singleton
//    fun provideApi(): Api {
//        return Retrofit.Builder()
//            .baseUrl("http://192.168.43.207/myapi/public/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(Api::class.java)
//    }
}