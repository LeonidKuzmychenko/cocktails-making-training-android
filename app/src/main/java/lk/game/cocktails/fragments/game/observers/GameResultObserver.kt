package lk.game.cocktails.fragments.game.observers

import android.content.Context
import android.view.Menu
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import lk.game.cocktails.R

class GameResultObserver(
    private val context: Context,
    private val menu: Menu
) : Observer<Boolean> {

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