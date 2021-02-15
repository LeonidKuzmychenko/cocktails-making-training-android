package lk.game.cocktails

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lk.game.cocktails.application.BaseActivity
import lk.game.cocktails.application.MyApplication
import lk.game.cocktails.databinding.ActivityMainBinding
import lk.game.cocktails.retrofit.repository.ApiRepository
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it) }) {

    @Inject
    lateinit var api: ApiRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MyApplication).getWebComponent()?.inject(this)
//        val model: MainViewModel by viewModels()//TODO
        val model = ViewModelProvider(this).get(MainViewModel::class.java)

        model.cocktails.observe(this, { cocktail ->
            Log.d(TAG, "LiveData = $cocktail")
        })

        binding.buttonRt.setOnClickListener {
            Log.d(TAG, "api = $api")
            CoroutineScope(Dispatchers.IO).launch {
                val answer = api.getCocktails()
                model.cocktails.postValue(answer)
            }
        }
    }

}