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

    override fun onAttach(context: Context) {
        Log.d(TAG, "onAttach")
        super.onAttach(context)
        (context as AppCompatActivity?)!!.supportActionBar!!.hide()
    }
//        val window =(context as AppCompatActivity?)!!.window.decorView // Hide the status bar.

//        val uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//        window.systemUiVisibility = uiOptions


//        val window = (context as AppCompatActivity?)!!.window // Hide the status bar.
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
//            window.setDecorFitsSystemWindows(true)
//        }

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, sis: Bundle?): View? {
        this.binding = FragmentLoadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = Navigation.findNavController(view).currentDestination!!.label
        (this@LoadFragment.activity as AppCompatActivity?)!!.title = title
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d(TAG, "onActivityCreated")
        super.onActivityCreated(savedInstanceState)



        viewModel = ViewModelProvider(this).get(LoadViewModel::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            for (i in 1..3) {
                Thread.sleep(1000)
            }
            CoroutineScope(Dispatchers.Main).launch {
                (this@LoadFragment.activity as AppCompatActivity?)!!.supportActionBar!!.show()
                Navigation.findNavController(this@LoadFragment.requireActivity(), R.id.nav_host_fragment).navigate(R.id.modeFragment)
            }
        }
    }

}