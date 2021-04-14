package lk.game.cocktails.application

import android.app.Application
import lk.game.cocktails.dagger.DaggerWebComponent
import lk.game.cocktails.dagger.WebComponent

class MyApplication : Application() {

    private lateinit var webComponent: WebComponent

    override fun onCreate() {
        super.onCreate()
//        webComponent = DaggerWebComponent.builder()
//            .webModule(WebModule())
//            .build()

        webComponent = DaggerWebComponent.create();
    }

    fun getWebComponent(): WebComponent {
        return webComponent
    }

}