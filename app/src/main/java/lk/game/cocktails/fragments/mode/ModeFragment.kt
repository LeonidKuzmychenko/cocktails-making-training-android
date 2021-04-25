package lk.game.cocktails.fragments.mode

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import lk.game.cocktails.TAG
import lk.game.cocktails.application.AppComponent
import lk.game.cocktails.application.BaseFragment
import lk.game.cocktails.databinding.FragmentModeBinding
import lk.game.cocktails.fragments.mode.adapter.ModeRecyclerViewAdapter
import lk.game.cocktails.retrofit.Api
import javax.inject.Inject

class ModeFragment : BaseFragment<FragmentModeBinding, ModeViewModel>() {

    @Inject
    lateinit var api: Api

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as AppComponent).getWebComponent().inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d(TAG, "onActivityCreated")
        super.onActivityCreated(savedInstanceState)
        viewModel.modes.observe(
            viewLifecycleOwner, {
                binding.progressBar.visibility = View.GONE
                binding.modeList.adapter = ModeRecyclerViewAdapter(it)
            })
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

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): FragmentModeBinding {
        return FragmentModeBinding.inflate(inflater, container, false)
    }

    override fun getViewModel(): Class<ModeViewModel> {
        return ModeViewModel::class.java
    }
}