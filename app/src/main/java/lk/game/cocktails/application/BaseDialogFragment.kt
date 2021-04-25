package lk.game.cocktails.application

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding

abstract class BaseDialogFragment<VB : ViewBinding> : DialogFragment() {

    protected lateinit var binding: VB
    private var baseActivity: AppCompatActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        baseActivity = context as AppCompatActivity
        binding = inflate(LayoutInflater.from(context))
    }

    override fun onDetach() {
        super.onDetach()
        baseActivity = null
    }

    abstract fun inflate(inflater: LayoutInflater): VB

    protected fun baseActivity(): AppCompatActivity {
        return baseActivity!!
    }
}