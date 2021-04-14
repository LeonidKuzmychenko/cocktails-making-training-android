package lk.game.cocktails.dagger.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import lk.game.cocktails.db.BookmarkDataStore
import javax.inject.Singleton

@Module
class DataStoreModule {

    @Provides
    @Singleton
    fun provideBookmarkDataStore(context: Context): BookmarkDataStore {
        return BookmarkDataStore(context)
    }
}