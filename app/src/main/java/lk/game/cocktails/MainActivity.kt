package lk.game.cocktails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import lk.game.cocktails.application.AppComponent
import lk.game.cocktails.application.BaseActivity
import lk.game.cocktails.dagger.annotation.named.Keys
import lk.game.cocktails.dagger.annotation.named.Qualifier
import lk.game.cocktails.databinding.ActivityMainBinding
import lk.game.cocktails.retrofit.Api
import lk.game.cocktails.shared.SharedPreferencesService
import javax.inject.Inject


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    @Inject
    lateinit var api: Api

    @Inject
    @Qualifier(Keys.LOCALE)
    lateinit var locale: String

    @Inject
    lateinit var sp: SharedPreferencesService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as AppComponent).getWebComponent().inject(this)
        configurationActionBar()
        sp.clearExcludeList()
        Log.d(TAG, "Api: $api")
        Log.d(TAG, "Locale: $locale")
        Log.d(TAG, "SharedPreferencesService data: ${sp.getExcludeList()}")
        Log.d(TAG, "-------------------------------------------------")
    }

    override fun onSupportNavigateUp(): Boolean {
        if (!(getNavController().navigateUp() || super.onSupportNavigateUp())) {
            onBackPressed()
        }
        return true
    }

    override fun inflate(layoutInflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    private fun getNavController(): NavController {
        val fragment: Fragment? = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val navHostFragment = fragment as NavHostFragment
        return navHostFragment.navController
    }

    private fun configurationActionBar() {
        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.loadFragment,
            R.id.menuFragment
        ).build()
        setupActionBarWithNavController(getNavController(), appBarConfiguration)
    }
}
