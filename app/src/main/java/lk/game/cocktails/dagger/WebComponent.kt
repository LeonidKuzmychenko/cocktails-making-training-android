package lk.game.cocktails.dagger

import dagger.Component
import lk.game.cocktails.MainActivity
import lk.game.cocktails.dagger.modules.AppModule
import lk.game.cocktails.dagger.modules.DataStoreModule
import lk.game.cocktails.dagger.modules.LocaleModule
import lk.game.cocktails.dagger.modules.WebModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataStoreModule::class, WebModule::class, LocaleModule::class])
interface WebComponent {

    fun inject(activity: MainActivity)

}