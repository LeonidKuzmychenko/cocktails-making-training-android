package lk.game.cocktails.fragments.mode

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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import lk.game.cocktails.TAG
import lk.game.cocktails.application.AppComponent
import lk.game.cocktails.databinding.FragmentModeBinding
import lk.game.cocktails.fragments.mode.adapter.ModeRecyclerViewAdapter
import lk.game.cocktails.retrofit.Api
import javax.inject.Inject

class ModeFragment : Fragment() {

    private lateinit var binding: FragmentModeBinding
    private lateinit var viewModel: ModeViewModel

    @Inject
    lateinit var api: Api

    override fun onAttach(context: Context) {
        Log.d(TAG, "onAttach")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        (context?.applicationContext as AppComponent).getWebComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, sis: Bundle?): View {
        Log.d(TAG, "onCreateView")
        this.binding = FragmentModeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        val title = Navigation.findNavController(requireView()).currentDestination!!.label
        (activity as AppCompatActivity?)!!.title = title
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d(TAG, "onActivityCreated")
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ModeViewModel::class.java)
        viewModel.modes.observe(
            viewLifecycleOwner,
            { binding.recyclerView2.adapter = ModeRecyclerViewAdapter(it) })
    }

    override fun onResume() {
        Log.d(TAG, "onResume")
        GlobalScope.launch {
            val response = api.getModes()
            Log.d(this@ModeFragment.TAG, "Response Code = ${response.code()}")
            val body = response.body()
            viewModel.modes.postValue(body)
        }
        super.onResume()
    }
}