package lk.game.cocktails.application

import android.app.Application
import lk.game.cocktails.dagger.DaggerWebComponent
import lk.game.cocktails.dagger.WebComponent
import lk.game.cocktails.dagger.modules.AppModule

class MyApplication : Application() {

    private lateinit var webComponent: WebComponent

    override fun onCreate() {
        super.onCreate()
//        webComponent = DaggerWebComponent.builder()
//            .webModule(WebModule())
//            .build()

        webComponent = DaggerWebComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun getWebComponent(): WebComponent {
        return webComponent
    }

}