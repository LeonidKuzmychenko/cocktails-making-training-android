package lk.game.cocktails.dagger

import dagger.Component
import lk.game.cocktails.MainActivity
import lk.game.cocktails.retrofit.Api
import java.util.*
import javax.inject.Singleton

@Singleton
@Component(modules = [WebModule::class])
interface WebComponent {
    fun inject(activity: MainActivity)
}