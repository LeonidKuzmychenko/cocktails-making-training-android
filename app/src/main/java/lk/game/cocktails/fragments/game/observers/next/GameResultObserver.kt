package lk.game.cocktails.fragments.game.observers.next

import android.content.Context
import android.view.Menu
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import lk.game.cocktails.R
import lk.game.cocktails.fragments.game.data.GameItemState
import lk.game.cocktails.fragments.game.observers.next.parent.AbstractGameNextCocktail
import lk.game.cocktails.retrofit.data.Cocktail
import lk.game.cocktails.retrofit.repository.ApiRepository

class GameResultObserver(
    private val context: Context,
    private val menu: Menu,
    cocktail: MutableLiveData<Cocktail>,
    checkers: MutableLiveData<MutableList<GameItemState>>,
    apiRepository: ApiRepository
) : AbstractGameNextCocktail(cocktail, checkers, apiRepository), Observer<Boolean> {

    override fun onChanged(it: Boolean) {
        val iconId = if (it) {
            R.drawable.alert_circle_outline
        } else {
            R.drawable.check_bold
        }

        val icon = ContextCompat.getDrawable(context, iconId)
        menu.findItem(R.id.nextCocktail).icon = icon
    }
}