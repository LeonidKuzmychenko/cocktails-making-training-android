package lk.game.cocktails.fragments.game.observers.cocktail

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import lk.game.cocktails.retrofit.data.Cocktail

class GameCocktailTitleObserver(
    private val activity: AppCompatActivity,
) : Observer<Cocktail> {

    override fun onChanged(cocktail: Cocktail) {
        activity.supportActionBar!!.title = cocktail.name
        activity.supportActionBar!!.subtitle = cocktail.association
    }

}