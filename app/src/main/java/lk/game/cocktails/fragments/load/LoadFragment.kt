package lk.game.cocktails.fragments.load

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lk.game.cocktails.TAG
import lk.game.cocktails.application.AppComponent
import lk.game.cocktails.application.BaseFragment
import lk.game.cocktails.databinding.FragmentLoadBinding
import lk.game.cocktails.retrofit.Api
import java.util.concurrent.TimeoutException
import javax.inject.Inject

class LoadFragment : BaseFragment<FragmentLoadBinding, LoadViewModel>() {

    @Inject
    lateinit var api: Api

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as AppComponent).getWebComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseActivity().supportActionBar!!.hide()
        baseActivity().supportActionBar!!.elevation = 0f
        baseActivity().title = Navigation.findNavController(view).currentDestination!!.label
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d(TAG, "onActivityCreated")
        super.onActivityCreated(savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            waitServerStart()
            CoroutineScope(Dispatchers.Main).launch {
                baseActivity().supportActionBar!!.show()
                val action = LoadFragmentDirections.actionLoadFragmentToMenuFragment()
                Navigation.findNavController(requireView()).navigate(action)
            }
        }
    }

    private suspend fun waitServerStart() {
        try {
            if (api.status().code() == 200)
                return
        } catch (e: TimeoutException) {
            e.printStackTrace()
        }
        return waitServerStart()
    }

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): FragmentLoadBinding {
        return FragmentLoadBinding.inflate(inflater, container, false)
    }

    override fun getViewModel(): Class<LoadViewModel> {
        return LoadViewModel::class.java
    }

}