package lk.game.cocktails.fragments.game.observers

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import lk.game.cocktails.R
import lk.game.cocktails.databinding.FragmentGameBinding
import lk.game.cocktails.fragments.game.adapters.GameRecyclerViewAdapter
import lk.game.cocktails.retrofit.data.Cocktail

class GameCocktailObserver(
    private val binding: FragmentGameBinding,
    private val checkers: MutableLiveData<MutableList<Boolean>>,
    private val serverName: String
) : Observer<Cocktail> {

    override fun onChanged(cocktail: Cocktail) {
        setImage(cocktail)
        setTitle(cocktail)
        setAdapter(cocktail)
    }

    private fun setImage(cocktail: Cocktail) {
        Glide.with(getActivity())
            .load(serverName + cocktail.photo)
            .fitCenter()
            .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE))
            .error(ContextCompat.getDrawable(getActivity(), R.drawable.error))
            .into(binding.cocktailImage)
    }

    private fun setAdapter(cocktail: Cocktail) {
        binding.ingredientsList.adapter = GameRecyclerViewAdapter(cocktail.ingredients, checkers)
    }

    private fun setTitle(cocktail: Cocktail) {
        getActivity().supportActionBar!!.title = cocktail.name
        getActivity().supportActionBar!!.subtitle = cocktail.association
    }

    private fun getActivity(): AppCompatActivity {
        return binding.root.context as AppCompatActivity
    }

}