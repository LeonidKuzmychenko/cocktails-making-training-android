package lk.game.cocktails.retrofit.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CocktailShort(

    @SerializedName("id")
    @Expose
    var id: Long,

    @SerializedName("name")
    @Expose
    var name: String,

    @SerializedName("photo")
    @Expose
    var photo: String,

    )