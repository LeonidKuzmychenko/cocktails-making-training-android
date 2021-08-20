package lk.game.cocktails.dagger

import dagger.Component
import lk.game.cocktails.MainActivity
import lk.game.cocktails.dagger.modules.*
import lk.game.cocktails.dagger.modules.web.ApiModule
import lk.game.cocktails.dagger.modules.web.RetrofitModule
import lk.game.cocktails.fragments.game.GameFragment
import lk.game.cocktails.fragments.library.LibraryFragment
import lk.game.cocktails.fragments.load.LoadFragment
import lk.game.cocktails.fragments.menu.MenuFragment
import lk.game.cocktails.fragments.mode.ModeFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        RetrofitModule::class,
        ApiModule::class,
        LocaleModule::class,
        CountryModule::class,
        IdentifierModule::class,
        SharedPreferencesModule::class,
        ServerModule::class]
)
interface WebComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: LoadFragment)
    fun inject(fragment: ModeFragment)
    fun inject(fragment: MenuFragment)
    fun inject(fragment: GameFragment)
    fun inject(fragment: LibraryFragment)

}