package lk.game.cocktails.fragments.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import lk.game.cocktails.retrofit.data.Cocktail

class GameViewModel : ViewModel() {
    val cocktail: MutableLiveData<Cocktail> = MutableLiveData()
    val checkers: MutableLiveData<MutableList<Boolean>> = MutableLiveData()
    val firstRun: MutableLiveData<Boolean> = MutableLiveData(true)
}