package lk.game.cocktails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import lk.game.cocktails.retrofit.data.Cocktail

class MainViewModel : ViewModel() {
//    val cocktails: MutableLiveData<List<Cocktail>> by lazy {
//        return@lazy MutableLiveData<List<Cocktail>>().also {
//
//        }
//    }//TODO

    val cocktails: MutableLiveData<List<Cocktail>> = MutableLiveData()

}

