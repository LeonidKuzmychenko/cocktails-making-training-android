package lk.game.cocktails.fragments.game.observers.cocktail

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import lk.game.cocktails.R
import lk.game.cocktails.retrofit.data.Cocktail

class GameCocktailImageObserver(
    private val image: ImageView,
    private val serverName: String
) : Observer<Cocktail> {

    override fun onChanged(cocktail: Cocktail) {
        Glide.with(image.context)
            .load(serverName + cocktail.photo)
            .fitCenter()
            .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
            .error(ContextCompat.getDrawable(image.context, R.drawable.error))
            .into(image)
    }

}