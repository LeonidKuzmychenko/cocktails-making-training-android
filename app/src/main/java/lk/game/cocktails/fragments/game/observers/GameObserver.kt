package lk.game.cocktails.fragments.game.observers

import android.annotation.SuppressLint
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import lk.game.cocktails.TAG
import lk.game.cocktails.databinding.FragmentGameBinding
import lk.game.cocktails.fragments.game.adapters.GameRecyclerViewAdapter
import lk.game.cocktails.retrofit.data.Cocktail

class GameObserver(
    private val activity: AppCompatActivity,
    private val binding: FragmentGameBinding,
    private val serverName: String
) : Observer<Cocktail> {

    @SuppressLint("SetTextI18n")
    override fun onChanged(cocktail: Cocktail) {
        val imagePath = serverName + cocktail?.photo?.substring(1, cocktail.photo.lastIndex + 1)
        Log.d(TAG, "Photo = $imagePath")
        Glide.with(binding.cocktailImage.context)
            .load(imagePath)
            .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE))
            .into(binding.cocktailImage)
        activity.supportActionBar!!.title = cocktail.name
        binding.cocktailDescription.text = "Association: ${cocktail.association}\n\n" +
                "Garnish: ${cocktail.garnish}\n\n" +
                "Method: ${cocktail.method}\n\n" +
                "Note: ${cocktail.note}\n\n" +
                "Type: ${cocktail.type}"
        binding.ingredientsList.adapter = GameRecyclerViewAdapter(cocktail.ingredients)
    }

}