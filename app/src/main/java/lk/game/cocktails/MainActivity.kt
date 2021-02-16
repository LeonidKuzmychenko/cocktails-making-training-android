package lk.game.cocktails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


//class MainActivity : BaseActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it) }) {
class MainActivity : AppCompatActivity() {

//    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        (application as MyApplication).getWebComponent()?.inject(this)
//        setupWithNavController()
//        navController.navigate(R.id.modeFragment)
    }

//    private fun setupWithNavController() {
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        navController = navHostFragment.navController
//        navController.setGraph(R.navigation.mobile_nav)
//    }

}

//        val model = ViewModelProvider(this).get(MainViewModel::class.java)
//        model.cocktails.observe(this, { cocktail ->
//            Log.d(TAG, "LiveData = $cocktail")
//        })
//        binding.buttonRt.setOnClickListener {
//            Log.d(TAG, "api = $api")
//            CoroutineScope(Dispatchers.IO).launch {
//                val answer = api.getCocktails()
//                model.cocktails.postValue(answer)
//            }
//        }