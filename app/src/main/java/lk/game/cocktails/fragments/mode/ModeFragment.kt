package lk.game.cocktails.fragments.mode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import lk.game.cocktails.R
import lk.game.cocktails.databinding.FragmentModeBinding

class ModeFragment : Fragment() {

    private lateinit var binding: FragmentModeBinding
    private lateinit var viewModel: ModeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        this.binding = FragmentModeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ModeViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ModeRecyclerView(listOf("1","2","3"))
        binding.recyclerView2.adapter = adapter
    }

}