package lk.game.cocktails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    public val users: MutableLiveData<List<String>> by lazy {
        return@lazy MutableLiveData<List<String>>().also {

        }
    }


}

