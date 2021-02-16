package lk.game.cocktails.fragments.mode

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import lk.game.cocktails.TAG
import lk.game.cocktails.databinding.FragmentModeBinding
import lk.game.cocktails.fragments.mode.adapter.ModeRecyclerViewAdapter

class ModeFragment : Fragment() {

    private lateinit var binding: FragmentModeBinding
    private lateinit var viewModel: ModeViewModel

    override fun onAttach(context: Context) {
        Log.d(TAG, "onAttach")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView")
        this.binding = FragmentModeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated")
        super.onViewCreated(view, savedInstanceState)
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
        super.onResume()
    }
}