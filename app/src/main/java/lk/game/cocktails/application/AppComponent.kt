package lk.game.cocktails.application

import android.app.Application
import lk.game.cocktails.dagger.DaggerWebComponent
import lk.game.cocktails.dagger.WebComponent
import lk.game.cocktails.dagger.modules.*

class AppComponent : Application() {

    private lateinit var webComponent: WebComponent

    override fun onCreate() {
        super.onCreate()
        webComponent = DaggerWebComponent.builder().appModule(AppModule(this)).build()
        initStatistic()
    }

    fun getWebComponent(): WebComponent {
        return webComponent
    }

    private fun initStatistic() {//FIXME
        val locale = LocaleModule().provideLocale()
        val country = CountryModule().provideCountry(this)
        val identifier = IdentifierModule().provideIdentifier(this)

        val sPref = SharedPreferencesModule().provideSharedPreferences(this)
        val sharedPrefStatistic = SharedPreferencesModule().provideSharedPrefStatisticService(sPref)

        sharedPrefStatistic.init(identifier, locale, country)
    }


}