package lk.game.cocktails.application

import android.app.Application
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import lk.game.cocktails.dagger.DaggerWebComponent
import lk.game.cocktails.dagger.WebComponent
import lk.game.cocktails.dagger.modules.AppModule

class AppComponent : Application() {

    private lateinit var mFirebaseAnalytics: FirebaseAnalytics
    private lateinit var webComponent: WebComponent

    override fun onCreate() {
        super.onCreate()
        mFirebaseAnalytics = Firebase.analytics
        webComponent = DaggerWebComponent.builder().appModule(AppModule(this)).build()
    }

    fun getWebComponent(): WebComponent {
        return webComponent
    }

    fun logGameAnalyticsEvent(cocktailName: String, result: Boolean) {
        val bundle = Bundle()
        bundle.putString("cocktail_name", cocktailName)
        bundle.putBoolean("result", result)
        mFirebaseAnalytics.logEvent("game_result", bundle)
    }

    fun logLibraryAnalyticsEvent(cocktailName: String) {
        val bundle = Bundle()
        bundle.putString("cocktail_name", cocktailName)
        mFirebaseAnalytics.logEvent("library_view", bundle)
    }

}