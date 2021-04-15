package lk.game.cocktails

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import lk.game.cocktails.application.MyApplication
import lk.game.cocktails.db.BookmarkDataStore
import lk.game.cocktails.retrofit.Api
import javax.inject.Inject
import javax.inject.Named

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var api: Api

    @Inject
    @Named("Locale")
    lateinit var locale: String

    @Inject
    lateinit var bookmarkDataStore: BookmarkDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as MyApplication).getWebComponent().inject(this)

        
        Log.d(TAG, "Dagger locale lang: $locale")

        bookmarkDataStore.getBookmark().asLiveData().observe(this, Observer {
            Log.d(this@MainActivity.TAG, "Bookmark = $it")
        })

        GlobalScope.launch {
            bookmarkDataStore.update("value1")
            Thread.sleep(1000)
            bookmarkDataStore.update("value2")
            Thread.sleep(1000)
            bookmarkDataStore.update("value3")
            Thread.sleep(1000)


        }

        val value1 = bookmarkDataStore.getValue()
        Log.d(this@MainActivity.TAG, "FINISH 1 = $value1")

        val value2 = bookmarkDataStore.getValueValue()
        Log.d(this@MainActivity.TAG, "FINISH 2 = $value2")
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

//        userPreferences = UserPreferences(this)
//
//        userPreferences.bookmark.asLiveData().observe(this, Observer {
//            Log.d(this@MainActivity.TAG,"Text = $it")
//        })
//
//
//        GlobalScope.launch{
//
//            val bookmark = UUID.randomUUID().toString()
//            userPreferences.saveBookmark(bookmark)
//
////            for (i in 1..5){
////                val bookmark = UUID.randomUUID().toString()
////                userPreferences.saveBookmark(bookmark)
////                Thread.sleep(3000)
////            }
//        }

//

//        val endResult = userPreferences.bookmark.asLiveData().value
//
//        Log.d(TAG,"End Result = $endResult")

//        GlobalScope.launch {
//            val employeeDao = db.employeeDao()
//            var savedResult = employeeDao.getById(0)
//            if (savedResult == null) {
//                savedResult = GameResult(0, "")
//                db.employeeDao().insert(savedResult)
//            }
//            Thread.sleep(1000)
    }

//    private suspend fun getCocktail() {
//        val employeeDao = db.employeeDao()
//        val savedResult = employeeDao.getById(0)
//        val excludes = savedResult!!.excludes
//        Log.d(this@MainActivity.TAG, "Excludes = $excludes")
//        val cocktail = api.getCocktail("RU", excludes, 10)
//        Log.d(this@MainActivity.TAG, "Cocktail = ${cocktail.body()}")
//        val toList: MutableList<Long> = if (excludes.isEmpty()) {
//            mutableListOf()
//        } else {
//            excludes.split(",").map { it.toLong() }.toMutableList()
//        }
//        toList.add(cocktail.body()!!.id)
//        Log.d(this@MainActivity.TAG, "Cocktail id = ${cocktail.body()!!.id}")
//        Log.d(this@MainActivity.TAG, "-------------------------------------------------")
//        savedResult.excludes = toList.joinToString(",")
//        db.employeeDao().update(savedResult)
//        Thread.sleep(1000)
//        getCocktail()
//    }


//    private fun gg(context: Context){
//        context.createDataStore
//    }

}
