package lk.game.cocktails.fragments.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import lk.game.cocktails.retrofit.data.Cocktail

class GameViewModel : ViewModel() {
    val cocktails: MutableLiveData<Cocktail> = MutableLiveData()
}