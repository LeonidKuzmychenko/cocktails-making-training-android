package lk.game.cocktails.application

import android.app.Application
import lk.game.cocktails.retrofit.Api
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication : Application(), KodeinAware {
    override val kodein = Kodein {
        bind<Retrofit>(tag = "retrofit") with singleton {
            Retrofit.Builder().baseUrl("http://192.168.43.207/myapi/public/")
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
        bind<Api>(tag = "api") with singleton { instance<Retrofit>(tag = "retrofit").create(Api::class.java) }
    }

//    override val kodeinTrigger = KodeinTrigger()
//
//    override fun onCreate() {
//        super.onCreate()
//        kodeinTrigger.trigger()
//    }
}