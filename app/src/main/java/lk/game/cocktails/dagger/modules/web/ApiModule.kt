package lk.game.cocktails.dagger.modules.web

import dagger.Module
import dagger.Provides
import lk.game.cocktails.retrofit.Api
import lk.game.cocktails.retrofit.repository.ApiRepository
import lk.game.cocktails.shared.SharedPrefCocktailService
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideApiRepository(api: Api, sp: SharedPrefCocktailService): ApiRepository {
        return ApiRepository(api, sp)
    }

}