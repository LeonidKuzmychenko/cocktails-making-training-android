package lk.game.cocktails

import android.os.Bundle
import android.util.Log
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
        val exc = listOf<Long>(
            1,
            2,
            3,
            4,
            5,
            6,
            7,
            8,
            9,
            10,
            11,
            12,
            13,
            14,
            15,
            16,
            17,
            18,
            19,
            20,
            21,
            22,
            23,
            24,
            25,
            26,
            27,
            28,
            29,
            30,
            31,
            32,
            33,
            34,
            35,
            36,
            37,
            38,
            39,
            40,
            41,
            42,
            43,
            44,
            45,
            46,
            47,
            48,
            49,
            50,
            51,
            52,
            53,
            54,
            55,
            56,
            57,
            58,
            59,
            60,
            61,
            62,
            63,
            64,
            65,
            66,
            67,
            68,
            69,
            70,
            71,
            72,
            73,
            74,
            75,
            76,
            77,
            78,
            79,
            80
        )
        for (item in exc) {
            sp.addExclude(item)
        }
        Log.d(TAG, "Api: $api")
        Log.d(TAG, "Locale: $locale")
        Log.d(TAG, "SharedPreferencesService data: ${sp.getExcludeList()}")
        Log.d(TAG, "-------------------------------------------------")

//        GlobalScope.launch {
//            getCocktail()
//        }
    }

    private suspend fun getCocktail() {
        val excludes = sp.getExcludeList()
        Log.d(this@MainActivity.TAG, "Excludes = $excludes")
        Log.d(this@MainActivity.TAG, "Excludes size = ${excludes.size}")
        val response = api.getCocktail("RU", excludes.joinToString(","), 10)
        val responseCode = response.code()
        Log.d(this@MainActivity.TAG, "Cocktail response code = $responseCode")
        if (responseCode == 200) {
            val cocktailId = response.body()!!.id
            sp.addExclude(cocktailId)
            Log.d(this@MainActivity.TAG, "Cocktail id = $cocktailId")
            Log.d(this@MainActivity.TAG, "-------------------------------------------------")
            Thread.sleep(300)
        } else if (responseCode == 215) {
            Log.d(this@MainActivity.TAG, "-------------------------------------------------")
            Log.d(this@MainActivity.TAG, "You are WIN")
            return
        }
        getCocktail()
    }

}
