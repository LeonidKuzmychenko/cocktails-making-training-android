package lk.game.cocktails

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import lk.game.cocktails.application.MyApplication
import lk.game.cocktails.retrofit.Api
import lk.game.cocktails.shared.SharedPreferencesService
import javax.inject.Inject
import javax.inject.Named

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var api: Api

    @Inject
    @Named("Locale")
    lateinit var locale: String

    @Inject
    lateinit var sp: SharedPreferencesService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as MyApplication).getWebComponent().inject(this)

        sp.clearExcludeList()
        Log.d(TAG, "Api: $api")
        Log.d(TAG, "Locale: $locale")
        Log.d(TAG, "SharedPreferencesService data: ${sp.getExcludeList()}")
        Log.d(TAG, "-------------------------------------------------")
    }

    private suspend fun getCocktail() {
        val excludes = sp.getExcludeList()
        Log.d(this@MainActivity.TAG, "Excludes = $excludes")
        Log.d(this@MainActivity.TAG, "Excludes size = ${excludes.size}")
        val response = api.getCocktail("RU", excludes.joinToString(","), 10)
        val responseCode = response.code()
        Log.d(this@MainActivity.TAG, "Cocktail response code = $responseCode")
        if (responseCode == 200){
            val cocktailId = response.body()!!.id
            sp.addExclude(cocktailId)
            Log.d(this@MainActivity.TAG, "Cocktail id = $cocktailId")
            Log.d(this@MainActivity.TAG, "-------------------------------------------------")
            Thread.sleep(200)
        }
        else if (responseCode == 215){
            Toast.makeText(this@MainActivity, "You are WIN", Toast.LENGTH_LONG).show()
            return
        }
        getCocktail()
    }

}
