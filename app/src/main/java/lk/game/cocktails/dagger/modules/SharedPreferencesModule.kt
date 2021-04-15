package lk.game.cocktails.dagger.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import lk.game.cocktails.shared.SharedPreferencesService
import javax.inject.Singleton

@Module
class SharedPreferencesModule {

    @Provides
    @Singleton
    fun provideSharedPreferencesService(context: Context): SharedPreferencesService {
        return SharedPreferencesService(context)
    }
}