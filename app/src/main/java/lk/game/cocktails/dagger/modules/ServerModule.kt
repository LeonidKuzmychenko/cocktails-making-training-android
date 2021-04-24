package lk.game.cocktails.dagger.modules

import dagger.Module
import dagger.Provides
import lk.game.cocktails.dagger.annotation.named.Keys
import lk.game.cocktails.dagger.annotation.named.Qualifier
import javax.inject.Singleton

@Module
class ServerModule {

    @Qualifier(Keys.SERVER_NAME)
    @Provides
    @Singleton
    fun provideHost(): String {
        return "http://cocktails-making-training.herokuapp.com/"
    }

}