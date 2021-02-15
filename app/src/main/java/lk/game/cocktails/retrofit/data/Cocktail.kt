package lk.game.cocktails.retrofit.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Cocktail(

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

    @SerializedName("ingredients")
    @Expose
    var ingredients: List<Ingredient>

)