package lk.game.cocktails.dagger

import dagger.Component
import lk.game.cocktails.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [WebModule::class])
interface WebComponent {
    fun inject(activity: MainActivity)
}