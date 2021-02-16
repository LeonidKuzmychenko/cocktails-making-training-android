package lk.game.cocktails.fragments.mode

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ModeViewModel : ViewModel() {
    val modes: MutableLiveData<List<String>> = MutableLiveData()
}