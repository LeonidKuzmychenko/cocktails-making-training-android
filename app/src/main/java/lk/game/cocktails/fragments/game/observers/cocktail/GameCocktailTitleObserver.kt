package lk.game.cocktails.fragments.game.observers.cocktail

import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import lk.game.cocktails.retrofit.data.Cocktail


class GameCocktailTitleObserver(
    private val activity: AppCompatActivity,
) : Observer<Cocktail> {

    override fun onChanged(cocktail: Cocktail) {
        activity.supportActionBar!!.title = cocktail.name

        val spannableAssociation = SpannableString(cocktail.association)
        spannableAssociation.setSpan(RelativeSizeSpan(0.7f), 0, spannableAssociation.length, 0)

        activity.supportActionBar!!.subtitle = spannableAssociation
    }

}