package lk.game.cocktails.db

//import androidx.datastore.createDataStore
import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import kotlinx.coroutines.flow.map
import lk.game.cocktails.Bookmark
import java.io.InputStream
import java.io.OutputStream

class BookmarkDataStore(
    context: Context
) {
    private val applicationContext = context.applicationContext

    val Context.myDataStore by dataStore(fileName = "bookmark.pb", serializer = BookmarkSerializer)

//    private val dataStore: DataStore<Bookmark>
//    init {
//        dataStore = applicationContext.createDataStore(
//            fileName = "bookmark.pb",
//            serializer = BookmarkSerializer
//        )
//    }

    val bookmark = this.applicationContext.myDataStore.data.map { bookmarkSchema ->
        bookmarkSchema.bookmark
    }

    suspend fun saveBookmark(bookmark: String) {
        this.applicationContext.myDataStore.updateData { currentBookmark ->
            currentBookmark.toBuilder()
                .setBookmark(bookmark)
                .build()
        }
    }

    object BookmarkSerializer : Serializer<Bookmark> {
        override fun readFrom(input: InputStream): Bookmark {
            try {
                return Bookmark.parseFrom(input)
            } catch (exception: InvalidProtocolBufferException) {
                throw CorruptionException("Cannot read proto.", exception)
            }
        }

        override fun writeTo(t: Bookmark, output: OutputStream) = t.writeTo(output)

//        override val defaultValue: Bookmark get() = Bookmark.newBuilder().build()
//        override val defaultValue: Bookmark get() = Bookmark.getDefaultInstance()
        override val defaultValue: Bookmark get() = Bookmark.newBuilder().setBookmark(null).build()
    }
}