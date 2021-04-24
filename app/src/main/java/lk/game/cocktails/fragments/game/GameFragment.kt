package lk.game.cocktails.fragments.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import lk.game.cocktails.application.BaseFragment
import lk.game.cocktails.databinding.FragmentGameBinding
import lk.game.cocktails.fragments.game.observers.GameObserver

class GameFragment : BaseFragment<FragmentGameBinding, GameViewModel>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.cocktails.observe(viewLifecycleOwner, GameObserver(binding))
    }

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): FragmentGameBinding {
        return FragmentGameBinding.inflate(inflater, container, false)
    }

    override fun getViewModel(): Class<GameViewModel> {
        return GameViewModel::class.java
    }

}