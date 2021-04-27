package lk.game.cocktails.fragments.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import lk.game.cocktails.base.BaseDialogFragment
import lk.game.cocktails.databinding.DialogCocktailWinBinding

class DialogWinCocktail : BaseDialogFragment<DialogCocktailWinBinding>() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        setTitle(binding.winTitle)
        setImage(binding.winImage)
        return AlertDialog.Builder(baseActivity()).setView(binding.root).create()
    }

    private fun setImage(winImage: ImageView) {
        //TODO
    }

    private fun setTitle(winTitle: TextView) {
        //TODO
    }

    override fun inflate(inflater: LayoutInflater): DialogCocktailWinBinding {
        return DialogCocktailWinBinding.inflate(inflater)
    }

}