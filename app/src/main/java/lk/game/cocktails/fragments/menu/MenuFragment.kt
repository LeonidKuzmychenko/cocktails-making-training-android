package lk.game.cocktails.fragments.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import lk.game.cocktails.application.BaseFragment
import lk.game.cocktails.databinding.FragmentMenuBinding

class MenuFragment : BaseFragment<FragmentMenuBinding, MenuViewModel>() {

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): FragmentMenuBinding {
        return FragmentMenuBinding.inflate(inflater, container, false)
    }

    override fun getViewModel(): Class<MenuViewModel> {
        return MenuViewModel::class.java
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.start.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(MenuFragmentDirections.actionMenuFragmentToModeFragment())
        }
    }

}