package lk.game.cocktails.fragments.game.observers.next

import android.content.Context
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.Menu
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import lk.game.cocktails.R

class GameResultObserver(
    private val context: Context,
    private val menu: Menu
) : Observer<Boolean> {

    override fun onChanged(it: Boolean) {
        val resource = if (it) R.string.game_next else R.string.game_confirm
        val title = context.resources.getString(resource)
        val spannableTitle = SpannableString(title)
        val color = ContextCompat.getColor(context, R.color.black)
        spannableTitle.setSpan(ForegroundColorSpan(color), 0, spannableTitle.length, 0)
        menu.findItem(R.id.nextCocktail).title = spannableTitle
    }
}