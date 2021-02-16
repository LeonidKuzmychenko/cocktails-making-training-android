package lk.game.cocktails.fragments.game.observers

import androidx.lifecycle.Observer
import lk.game.cocktails.databinding.FragmentGameBinding
import lk.game.cocktails.fragments.game.adapters.GameRecyclerViewAdapter
import lk.game.cocktails.retrofit.data.Cocktail

class GameObserver(private val binding: FragmentGameBinding) : Observer<Cocktail> {

    override fun onChanged(cocktail: Cocktail) {
        binding.recyclerView.adapter = GameRecyclerViewAdapter(cocktail.ingredients)
    }

}