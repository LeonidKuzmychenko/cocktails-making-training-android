package lk.game.cocktails.fragments.load

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lk.game.cocktails.R
import lk.game.cocktails.TAG
import lk.game.cocktails.databinding.FragmentLoadBinding

class LoadFragment : Fragment() {

    private lateinit var binding: FragmentLoadBinding
    private lateinit var viewModel: LoadViewModel
    private lateinit var activity: AppCompatActivity

    override fun onAttach(context: Context) {
        Log.d(TAG, "onAttach")
        super.onAttach(context)
        activity = context as AppCompatActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        sis: Bundle?
    ): View? {
        this.binding = FragmentLoadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity.supportActionBar!!.hide()
        activity.supportActionBar!!.elevation = 0f
        activity.title = Navigation.findNavController(view).currentDestination!!.label
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d(TAG, "onActivityCreated")
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoadViewModel::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            Thread.sleep(2000)
            CoroutineScope(Dispatchers.Main).launch {
                activity.supportActionBar!!.show()
                Navigation.findNavController(
                    this@LoadFragment.requireActivity(),
                    R.id.nav_host_fragment
                ).navigate(R.id.modeFragment)
            }
        }
    }

}