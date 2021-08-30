package lk.game.cocktails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.gson.GsonBuilder
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import lk.game.cocktails.application.AppComponent
import lk.game.cocktails.base.BaseActivity
import lk.game.cocktails.databinding.ActivityMainBinding
import lk.game.cocktails.retrofit.Api
import lk.game.cocktails.shared.SharedPrefCocktailService
import lk.game.cocktails.statistics.services.SharedPrefStatisticService
import lk.game.cocktails.utils.TAG
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    @Inject
    lateinit var api: Api

    @Inject
    lateinit var sharedPrefCocktails: SharedPrefCocktailService

    @Inject
    lateinit var sharedPrefStatistic: SharedPrefStatisticService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as AppComponent).getWebComponent().inject(this)
        configurationActionBar()
        sharedPrefCocktails.clearExcludeList() //TODO remove on prod
        Log.d(TAG, "onCreate")
    }

    override fun onStop() {
        GlobalScope.launch {
            try {
                val data = sharedPrefStatistic.getStatistic()
                Log.d(TAG, "onStop = ${GsonBuilder().setPrettyPrinting().create().toJson(data)}")
                api.saveStatistic(data)
                sharedPrefStatistic.clearStatistic()
            } catch (e: Exception) {
            }
        }
        super.onStop()
    }

    override fun onSupportNavigateUp(): Boolean {
        if (!(getNavController().navigateUp() || super.onSupportNavigateUp())) {
            onBackPressed()
        }
        return true
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
        supportActionBar!!.elevation = 0f
    }

    override fun inflate(layoutInflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }
}
