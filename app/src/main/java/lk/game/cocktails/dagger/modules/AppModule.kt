package lk.game.cocktails.dagger.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val mApplication: Application) {

//    @Provides
//    @Singleton
//    fun providesApplication(): Application {
//        return mApplication
//    }

    @Provides
    @Singleton
    fun providesApplicationContext(): Context {
        return mApplication
    }
}