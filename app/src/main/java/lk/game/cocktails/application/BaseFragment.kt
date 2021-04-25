package lk.game.cocktails.application

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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
        if (clearSubTitle()){
            baseActivity().supportActionBar!!.subtitle = null
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, sis: Bundle?): View {
        binding = inflate(inflater, container)
        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        baseActivity = null
    }

    abstract fun inflate(inflater: LayoutInflater, container: ViewGroup?): VB

    abstract fun getViewModel(): Class<VM> //TODO

    protected fun baseActivity(): AppCompatActivity {
        return baseActivity!!
    }

    protected open fun clearSubTitle(): Boolean {
        return true
    }
}