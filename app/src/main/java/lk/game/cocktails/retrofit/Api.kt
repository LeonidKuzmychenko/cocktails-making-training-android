package lk.game.cocktails.retrofit

import lk.game.cocktails.retrofit.data.Cocktail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("ui/getAll/")
    suspend fun getCocktails(
        @Query("locale") locale: String,
        @Query("cSize") cSize: Long,
        @Query("iSize") iSize: Long
    ): Response<List<Cocktail>>

}