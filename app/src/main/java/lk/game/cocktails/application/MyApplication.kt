package lk.game.cocktails.application

import android.app.Application
import lk.game.cocktails.dagger.DaggerWebComponent
import lk.game.cocktails.dagger.WebComponent
import lk.game.cocktails.dagger.modules.AppModule
import lk.game.cocktails.dagger.modules.RoomModule
import lk.game.cocktails.dagger.modules.WebModule

class MyApplication : Application() {

    private lateinit var webComponent: WebComponent

    override fun onCreate() {
        super.onCreate()
        webComponent = DaggerWebComponent.builder()
            .webModule(WebModule())
            .roomModule(RoomModule(this))
            .build()

//        webComponent = DaggerWebComponent.create();
    }

    fun getWebComponent(): WebComponent {
        return webComponent
    }

}