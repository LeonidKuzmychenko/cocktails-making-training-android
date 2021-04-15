package lk.game.cocktails.dagger.modules

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import lk.game.cocktails.shared.SharedPreferencesService
import javax.inject.Singleton

@Module
class SharedPreferencesModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("mysettings", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideSharedPreferencesService(sp: SharedPreferences): SharedPreferencesService {
        return SharedPreferencesService(sp)
    }
}