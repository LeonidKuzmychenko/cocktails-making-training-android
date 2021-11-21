package lk.game.cocktails.dagger.modules

import dagger.Module
import dagger.Provides
import lk.game.cocktails.dagger.annotation.named.Keys
import lk.game.cocktails.dagger.annotation.named.Qualifier
import java.util.*
import javax.inject.Singleton

@Module
class LocaleModule {

    @Qualifier(Keys.LOCALE)
    @Provides
    @Singleton
    fun provideLocale(): String {
        return Locale.getDefault().language.toUpperCase(Locale.ROOT)
    }

}