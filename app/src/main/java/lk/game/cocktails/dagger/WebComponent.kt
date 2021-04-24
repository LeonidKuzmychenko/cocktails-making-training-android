package lk.game.cocktails.dagger

import dagger.Component
import lk.game.cocktails.MainActivity
import lk.game.cocktails.dagger.modules.*
import lk.game.cocktails.fragments.MenuFragment
import lk.game.cocktails.fragments.game.GameFragment
import lk.game.cocktails.fragments.load.LoadFragment
import lk.game.cocktails.fragments.mode.ModeFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, WebModule::class, LocaleModule::class, SharedPreferencesModule::class, ServerModule::class])
interface WebComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: LoadFragment)
    fun inject(fragment: ModeFragment)
    fun inject(fragment: MenuFragment)
    fun inject(fragment: GameFragment)

}