package lk.game.cocktails.retrofit.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Ingredient(

    @SerializedName("name")
    @Expose
    var name: String,

    @SerializedName("consists")
    @Expose
    var consists: Boolean

)