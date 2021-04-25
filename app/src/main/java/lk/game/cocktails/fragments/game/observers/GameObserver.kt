package lk.game.cocktails.fragments.game.observers

import android.annotation.SuppressLint
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import lk.game.cocktails.R
import lk.game.cocktails.TAG
import lk.game.cocktails.databinding.FragmentGameBinding
import lk.game.cocktails.fragments.game.GameViewModel
import lk.game.cocktails.fragments.game.adapters.GameRecyclerViewAdapter
import lk.game.cocktails.retrofit.data.Cocktail

class GameObserver(
    private val activity: AppCompatActivity,
    private val binding: FragmentGameBinding,
    private val viewModel: GameViewModel,
    private val serverName: String
) : Observer<Cocktail> {

    @SuppressLint("SetTextI18n")
    override fun onChanged(cocktail: Cocktail) {
        val imagePath = serverName + cocktail.photo.substring(1, cocktail.photo.lastIndex + 1)
        Log.d(TAG, "Photo = $imagePath")
        Glide.with(binding.cocktailImage.context)
            .load(imagePath)
            .fitCenter()
            .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE))
            .error(ContextCompat.getDrawable(activity, R.drawable.error))
            .into(binding.cocktailImage)
        activity.supportActionBar!!.title = cocktail.name
        activity.supportActionBar!!.subtitle = cocktail.association

        val type = cocktail.type
        val method = cocktail.method
        val garnish = cocktail.garnish
        val note = cocktail.note

        val description = StringBuilder()
        if (type.trim().isNotEmpty() && type.trim() != "-") {
            description.append("Тип коктейля: $type")
        }
        if (method.trim().isNotEmpty() && method.trim() != "-") {
            description.append("\n\nМетод приготовления: $method")
        }
        if (garnish.trim().isNotEmpty() && garnish.trim() != "-") {
            description.append("\n\nУкрашение: $garnish")
        }
        if (note.trim().isNotEmpty() && note.trim() != "-") {
            description.append("\n\nПримечание: $note")
        }

//        binding.cocktailDescription.text = description.toString()
        binding.ingredientsList.adapter =
            GameRecyclerViewAdapter(cocktail.ingredients, viewModel.checkers)
    }

}