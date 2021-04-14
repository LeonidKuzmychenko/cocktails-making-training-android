package lk.game.cocktails.db

import android.content.Context
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.Flow
import lk.game.cocktails.Bookmark

class UserPreferences(context: Context) {

    private val applicationContext = context.applicationContext

    private val Context.myDataStore2 by dataStore(fileName = "bookmark.pb", serializer = BookmarkDataStore.BookmarkSerializer)

    val bookmark: Flow<Bookmark?> = this.applicationContext.myDataStore2.data

    suspend fun saveBookmark(bookmark: Bookmark) {
        this.applicationContext.myDataStore2.updateData{ b0:Bookmark -> bookmark }
    }

    companion object {
        val KEY_BOOKMARK = stringPreferencesKey("key_bookmark")
    }
}