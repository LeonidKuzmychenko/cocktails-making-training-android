package lk.game.cocktails

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import lk.game.cocktails.application.AppComponent
import lk.game.cocktails.base.BaseActivity
import lk.game.cocktails.databinding.ActivityMainBinding
import lk.game.cocktails.shared.SharedPreferencesService
import javax.inject.Inject


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    @Inject
    lateinit var sp: SharedPreferencesService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as AppComponent).getWebComponent().inject(this)
        configurationActionBar()
        sp.clearExcludeList() //TODO remove on prod
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
