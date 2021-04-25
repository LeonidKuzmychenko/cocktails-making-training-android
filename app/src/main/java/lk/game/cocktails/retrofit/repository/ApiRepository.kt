package lk.game.cocktails.retrofit.repository

import lk.game.cocktails.retrofit.Api
import lk.game.cocktails.retrofit.data.Cocktail
import lk.game.cocktails.shared.SharedPreferencesService

class ApiRepository(private val api: Api, private val sp: SharedPreferencesService) {

    suspend fun getCocktail(iSize: Long): Cocktail {
        val excludes = sp.getExcludeList().joinToString(",")
        val response = api.getCocktail(excludes, iSize)
        val responseCode = response.code()
        if (responseCode == 215) {
            throw RuntimeException("You are win!!!")
        }
        if (responseCode != 200) {
            throw RuntimeException("ERROR")
        }
        val cocktail = response.body()
        sp.addExclude(cocktail!!.id)
        return cocktail
    }

}