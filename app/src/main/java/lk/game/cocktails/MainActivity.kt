package lk.game.cocktails

import android.os.Bundle
import android.util.Log
import lk.game.cocktails.application.BaseActivity
import lk.game.cocktails.databinding.ActivityMainBinding
import lk.game.cocktails.retrofit.Api
import org.kodein.di.generic.instance

class MainActivity : BaseActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it) }) {

    private val api: Api by instance(tag = "api")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.buttonRt.setOnClickListener {
            Log.d("TAG_RETROFIT", "api = $api")
        }
    }

}