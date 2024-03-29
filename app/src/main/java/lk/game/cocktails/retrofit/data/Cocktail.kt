package lk.game.cocktails.retrofit.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Cocktail(

    @SerializedName("id")
    @Expose
    var id: Long,

    @SerializedName("name")
    @Expose
    var name: String,

    @SerializedName("association")
    @Expose
    var association: String,

    @SerializedName("type")
    @Expose
    var type: String,

    @SerializedName("method")
    @Expose
    var method: String,

    @SerializedName("note")
    @Expose
    var note: String,

    @SerializedName("garnish")
    @Expose
    var garnish: String,

    @SerializedName("photo")
    @Expose
    var photo: String,

    @SerializedName("ingredients")
    @Expose
    var ingredients: List<Ingredient>

) : Serializable {
    fun countConsists(): Int {
        var count = 0
        for (i in ingredients.indices) {
            if (ingredients[i].consists) {
                count++
            }
        }
        return count
    }
}