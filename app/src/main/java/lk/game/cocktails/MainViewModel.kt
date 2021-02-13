package lk.game.cocktails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    public val users: MutableLiveData<List<String>> by lazy {
        return@lazy MutableLiveData<List<String>>().also {

        }
    }


}

