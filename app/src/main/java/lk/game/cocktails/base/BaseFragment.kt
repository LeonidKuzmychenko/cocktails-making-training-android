package lk.game.cocktails.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel> : Fragment() {

    protected lateinit var binding: VB
    protected lateinit var viewModel: VM
    private var baseActivity: AppCompatActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        baseActivity = context as AppCompatActivity
        viewModel = ViewModelProvider(this).get(getViewModel())
    }

    override fun onResume() {
        super.onResume()
        if (clearSubTitle()) {
            baseActivity().supportActionBar!!.subtitle = null
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, sis: Bundle?): View {
        binding = inflate(inflater, container)
        return binding.root
    }

    abstract fun inflate(inflater: LayoutInflater, container: ViewGroup?): VB

    abstract fun getViewModel(): Class<VM> //TODO

    fun baseActivity(): AppCompatActivity {
        return baseActivity!!
    }

    fun lifecycle(): LifecycleOwner {
        return viewLifecycleOwner
    }

    protected open fun clearSubTitle(): Boolean {
        return true
    }
}