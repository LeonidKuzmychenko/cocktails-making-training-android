package lk.game.cocktails.fragments.mode

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import lk.game.cocktails.retrofit.data.Mode

class ModeViewModel : ViewModel() {
    val modes: MutableLiveData<List<Mode>> = MutableLiveData()
}