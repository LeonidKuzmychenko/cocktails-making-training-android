package lk.game.cocktails.fragments.load

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lk.game.cocktails.R

class LoadFragment : Fragment() {

    private lateinit var viewModel: LoadViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_load, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoadViewModel::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            for (i in 1..5) {
                Thread.sleep(1500)
                CoroutineScope(Dispatchers.Main).launch {
                    Toast.makeText(context, "$i", Toast.LENGTH_SHORT).show()
                }
            }
            CoroutineScope(Dispatchers.Main).launch {
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                    .navigate(R.id.modeFragment)
            }
        }

    }

}