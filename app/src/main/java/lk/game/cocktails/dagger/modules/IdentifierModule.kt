package lk.game.cocktails.dagger.modules

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import dagger.Module
import dagger.Provides
import lk.game.cocktails.dagger.annotation.named.Keys
import lk.game.cocktails.dagger.annotation.named.Qualifier
import javax.inject.Singleton

@Module
class IdentifierModule {

    @SuppressLint("HardwareIds")
    @Qualifier(Keys.IDENTIFIER)
    @Provides
    @Singleton
    fun provideIdentifier(context: Context): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }

}