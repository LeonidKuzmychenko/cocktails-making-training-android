package lk.game.cocktails.fragments.game.observers.cocktail

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import lk.game.cocktails.fragments.game.adapters.GameRecyclerViewAdapter
import lk.game.cocktails.fragments.game.data.GameItemState
import lk.game.cocktails.retrofit.data.Cocktail

class GameCocktailAdapterObserver(
    private val owner: LifecycleOwner,
    private val cocktail: MutableLiveData<Cocktail>,
    private val checkers: MutableLiveData<MutableList<GameItemState>>,
    private val result: MutableLiveData<Boolean>,
    private val ingredientsView: RecyclerView
) : Observer<Cocktail> {

    override fun onChanged(cocktail: Cocktail) {
        ingredientsView.adapter =
            GameRecyclerViewAdapter(owner, this.cocktail, checkers, result, cocktail.ingredients)
    }

}