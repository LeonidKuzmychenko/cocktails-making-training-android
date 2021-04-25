package lk.game.cocktails.fragments.load

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
import lk.game.cocktails.application.BaseFragment
import lk.game.cocktails.databinding.FragmentLoadBinding

class LoadFragment : BaseFragment<FragmentLoadBinding, LoadViewModel>() {

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
            Thread.sleep(2000)
            CoroutineScope(Dispatchers.Main).launch {
                baseActivity().supportActionBar!!.show()
                val action = LoadFragmentDirections.actionLoadFragmentToMenuFragment()
                Navigation.findNavController(requireView()).navigate(action)
            }
        }
    }

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): FragmentLoadBinding {
        return FragmentLoadBinding.inflate(inflater, container, false)
    }

    override fun getViewModel(): Class<LoadViewModel> {
        return LoadViewModel::class.java
    }

}