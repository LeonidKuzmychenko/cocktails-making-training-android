package lk.game.cocktails.dagger.modules

import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Named
import javax.inject.Singleton

@Module
class LocaleModule {

    @Named("Locale")
    @Provides
    @Singleton
    fun provideLocale(): String {
        return Locale.getDefault().language.toUpperCase(Locale.ROOT)
    }

}