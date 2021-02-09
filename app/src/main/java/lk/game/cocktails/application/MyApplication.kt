package lk.game.cocktails.application

import android.app.Application
import lk.game.cocktails.dagger.DaggerWebComponent
import lk.game.cocktails.dagger.WebComponent


class MyApplication : Application() {

    private var component: WebComponent? = null

    override fun onCreate() {
        super.onCreate()
        component = DaggerWebComponent.create()
    }

    fun getWebComponent(): WebComponent? {
        return component
    }

}