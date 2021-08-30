package lk.game.cocktails.fragments.library

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import lk.game.cocktails.retrofit.data.CocktailShort

class LibraryViewModel : ViewModel() {
    val cocktails: MutableLiveData<List<CocktailShort>> = MutableLiveData()
}