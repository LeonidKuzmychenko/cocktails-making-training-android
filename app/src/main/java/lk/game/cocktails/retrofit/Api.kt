package lk.game.cocktails.retrofit

import lk.game.cocktails.retrofit.data.Cocktail
import lk.game.cocktails.retrofit.data.CocktailShort
import lk.game.cocktails.retrofit.data.Mode
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("/ui/cocktail/one")
    suspend fun getCocktail(
        @Query("exclude") exclude: String,
        @Query("iSize") iSize: Long
    ): Response<Cocktail>

    @GET("/ui/cocktail/all/short")
    suspend fun getCocktailsShort(): Response<List<CocktailShort>>

    @GET("/ui/mode/all")
    suspend fun getModes(): Response<List<Mode>>

    @GET("/status")
    suspend fun status(): Response<Void>
}