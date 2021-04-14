package lk.game.cocktails.db

import android.content.Context
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import kotlinx.coroutines.flow.map
import lk.game.cocktails.Bookmark
import java.io.InputStream
import java.io.OutputStream

class BookmarkDataStore(context: Context) {
    private val applicationContext = context.applicationContext

    private val Context.myDataStore by dataStore(
        fileName = "bookmark.pb",
        serializer = BookmarkSerializer
    )

    val bookmark = this.applicationContext.myDataStore.data.map { bookmarkSchema ->
        bookmarkSchema.bookmark
    }

    suspend fun saveBookmark(bookmark: String) {
        this.applicationContext.myDataStore.updateData {
            it.toBuilder()
                .setBookmark(bookmark)
                .build()
        }
    }

    object BookmarkSerializer : Serializer<Bookmark> {
        override suspend fun readFrom(inputStream: InputStream): Bookmark = Bookmark.parseFrom(inputStream)
        override suspend fun writeTo(bookmark: Bookmark, output: OutputStream): Unit = bookmark.writeTo(output)
        override val defaultValue: Bookmark get() = Bookmark.newBuilder().setBookmark(null).build()
    }
}