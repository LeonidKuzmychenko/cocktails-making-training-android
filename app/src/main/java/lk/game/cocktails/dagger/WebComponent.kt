package lk.game.cocktails.dagger

import dagger.Component
import lk.game.cocktails.MainActivity
import lk.game.cocktails.dagger.modules.LocaleModule
import lk.game.cocktails.dagger.modules.WebModule
import javax.inject.Singleton

@Singleton
@Component(modules = [WebModule::class, LocaleModule::class])
interface WebComponent {

    fun inject(activity: MainActivity)

}