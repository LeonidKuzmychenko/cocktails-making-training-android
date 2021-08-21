package lk.game.cocktails.fragments.game.services

import lk.game.cocktails.fragments.game.GameViewModel
import lk.game.cocktails.fragments.game.data.GameItemState
import lk.game.cocktails.statistics.services.SharedPrefStatisticService

class CheckResultService(
    private val viewModel: GameViewModel,
    private val sharedPrefStatistic: SharedPrefStatisticService
) {
    fun checkResult() {
        val iSize: Long = 12
        var endResult: Boolean? = null
        val ingredients = viewModel.checkers.value

        if (ingredients != null && ingredients.size >= iSize) {
            endResult = true
            for (i in 0..iSize) {
                val result = ingredients[i.toInt()]
                if (result == GameItemState.WRONG || result == GameItemState.MISSED) {
                    endResult = false
                    break
                }
            }
        }
        endResult?.let {
            sharedPrefStatistic.addGameResult(viewModel.cocktail.value!!.name, it)
        }
    }
}