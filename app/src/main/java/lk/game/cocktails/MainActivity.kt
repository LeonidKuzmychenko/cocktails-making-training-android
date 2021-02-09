package lk.game.cocktails

import android.os.Bundle
import android.util.Log
import lk.game.cocktails.application.BaseActivity
import lk.game.cocktails.application.MyApplication
import lk.game.cocktails.databinding.ActivityMainBinding
import lk.game.cocktails.retrofit.Api
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

class MainActivity : BaseActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it) }) {

    @Inject
    @Named("api1")
    lateinit var api1: Api

    @Inject
    @Named("api2")
    lateinit var api2: Api

    @Inject
    lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MyApplication).getWebComponent()?.inject(this)
        binding.buttonRt.setOnClickListener {
            Log.d("TAG_RETROFIT", "api = $api1")
            Log.d("TAG_RETROFIT", "ap2 = $api2")
            Log.d("TAG_RETROFIT", "retrofit = $retrofit")
            Log.d("TAG_RETROFIT", "____________________________________")
        }
    }

}