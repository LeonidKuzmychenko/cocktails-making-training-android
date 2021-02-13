package lk.game.cocktails

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import lk.game.cocktails.application.BaseActivity
import lk.game.cocktails.application.MyApplication
import lk.game.cocktails.databinding.ActivityMainBinding
import lk.game.cocktails.retrofit.Api
import java.util.*
import javax.inject.Inject
import javax.inject.Named

class MainActivity : BaseActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it) }) {

    @Inject
    lateinit var api: Api

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MyApplication).getWebComponent()?.inject(this)
//        val model: MainViewModel by viewModels()//TODO
        val model = ViewModelProvider(this).get(MainViewModel::class.java)

        model.users.observe(this, { users ->
            Log.d(TAG,"this")
        })

        model.users.value = mutableListOf("dwdwdw")

        binding.buttonRt.setOnClickListener {
            Log.d(TAG, "api = $api")
        }

    }

}