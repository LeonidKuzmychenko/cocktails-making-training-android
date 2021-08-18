package lk.game.cocktails.dagger.modules

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import lk.game.cocktails.shared.SharedPrefCocktailService
import lk.game.cocktails.statistics.services.SharedPrefStatisticService
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
    fun provideSharedPrefCocktailService(sp: SharedPreferences): SharedPrefCocktailService {
        return SharedPrefCocktailService(sp)
    }

    @Provides
    @Singleton
    fun provideSharedPrefStatisticService(sp: SharedPreferences): SharedPrefStatisticService {
        return SharedPrefStatisticService(sp, Gson())
    }
}