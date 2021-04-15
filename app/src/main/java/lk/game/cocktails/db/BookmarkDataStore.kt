package lk.game.cocktails.db

import android.content.Context
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import kotlinx.coroutines.flow.Flow
import lk.game.cocktails.Bookmark
import java.io.InputStream
import java.io.OutputStream

class BookmarkDataStore(context: Context) {
    private val Context.myDataStore by dataStore(
        fileName = "bookmark.pb",
        serializer = BookmarkSerializer
    )
    private val applicationContext = context.applicationContext
    private val bookmark = this.applicationContext.myDataStore.data


    fun getBookmark(): Flow<Bookmark> {
        return bookmark
    }

    fun getValue(): Bookmark? {
        return bookmark.asLiveData().value
    }

    fun getValueValue(): String? {
        return bookmark.asLiveData().value?.bookmark
    }

    suspend fun update(value: String) {
        this.applicationContext.myDataStore.updateData {
            it.toBuilder()
                .setBookmark(value)
                .build()
        }
    }

    suspend fun clear(value: String) {
        this.applicationContext.myDataStore.updateData {
            it.toBuilder()
                .clearBookmark()
                .build()
        }
    }

    object BookmarkSerializer : Serializer<Bookmark> {
        override suspend fun readFrom(inputStream: InputStream): Bookmark =
            Bookmark.parseFrom(inputStream)

        override suspend fun writeTo(bookmark: Bookmark, output: OutputStream): Unit =
            bookmark.writeTo(output)

        override val defaultValue: Bookmark get() = Bookmark.getDefaultInstance()
    }
}