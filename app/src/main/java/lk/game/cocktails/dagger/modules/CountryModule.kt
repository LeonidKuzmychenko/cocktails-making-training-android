package lk.game.cocktails.dagger.modules

import android.content.Context
import android.os.Build
import dagger.Module
import dagger.Provides
import lk.game.cocktails.dagger.annotation.named.Keys
import lk.game.cocktails.dagger.annotation.named.Qualifier
import javax.inject.Singleton

@Module
class CountryModule {

    @Qualifier(Keys.COUNTRY)
    @Provides
    @Singleton
    fun provideCountry(context: Context): String {
//        return Locale.getDefault().country
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.resources.configuration.locales.get(0).country
        } else {
            context.resources.configuration.locale.country
        }
    }

}