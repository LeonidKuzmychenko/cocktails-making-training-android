package lk.game.cocktails.fragments.game.services

import android.util.Log
import lk.game.cocktails.databinding.FragmentGameBinding
import lk.game.cocktails.fragments.game.GameViewModel
import lk.game.cocktails.fragments.game.data.GameItemState
import lk.game.cocktails.utils.TAG

class CountIngredientsService(
    private val viewModel: GameViewModel,
    private val binding: FragmentGameBinding
) {

    fun checkIngredientsCount() {
        val checkers: MutableList<GameItemState> = viewModel.checkers.value!!
        var count1 = 0
        val count2 = viewModel.cocktail.value!!.countConsists()
        for (i in 0 until checkers.size) {
            if (checkers[i] == GameItemState.SELECTED) {
                count1++
            }
        }
        Log.d(TAG, "Ingredients = $count1/$count2")
        binding.textView2?.text = "Ingredients = $count1/$count2"
    }
}