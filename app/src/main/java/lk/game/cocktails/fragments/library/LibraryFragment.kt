package lk.game.cocktails.fragments.library

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import lk.game.cocktails.application.AppComponent
import lk.game.cocktails.base.BaseFragment
import lk.game.cocktails.dagger.annotation.named.Keys
import lk.game.cocktails.dagger.annotation.named.Qualifier
import lk.game.cocktails.databinding.FragmentLibraryBinding
import lk.game.cocktails.fragments.library.adapter.LibraryRecyclerViewAdapter
import lk.game.cocktails.retrofit.Api
import lk.game.cocktails.utils.TAG
import javax.inject.Inject

class LibraryFragment : BaseFragment<FragmentLibraryBinding, LibraryViewModel>() {

    @Inject
    lateinit var api: Api

    @Inject
    @Qualifier(Keys.SERVER_NAME)
    lateinit var serverName: String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as AppComponent).getWebComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.cocktails.observe(lifecycle(), {
            binding.libraryList.adapter = LibraryRecyclerViewAdapter(it, serverName)
        })
    }

    override fun onResume() {
        Log.d(TAG, "onResume")
        GlobalScope.launch {
            val response = api.getCocktailsShort()
            Log.d(TAG, "Response Code = ${response.code()}")
            val body = response.body()
            viewModel.cocktails.postValue(body)
        }
        super.onResume()
    }

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): FragmentLibraryBinding {
        return FragmentLibraryBinding.inflate(inflater)
    }

    override fun getViewModel(): Class<LibraryViewModel> {
        return LibraryViewModel::class.java
    }

}