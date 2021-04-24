package lk.game.cocktails.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import lk.game.cocktails.application.BaseFragment
import lk.game.cocktails.databinding.FragmentMenuBinding

class MenuFragment : BaseFragment<FragmentMenuBinding, MenuViewModel>() {

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): FragmentMenuBinding {
        return FragmentMenuBinding.inflate(inflater, container, false)
    }

    override fun getViewModel(): Class<MenuViewModel> {
        return MenuViewModel::class.java
    }

}