package lk.game.cocktails.fragments.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import lk.game.cocktails.base.BaseDialogFragment
import lk.game.cocktails.databinding.DialogCocktailInfoBinding
import lk.game.cocktails.retrofit.data.Cocktail
import lk.game.cocktails.utils.DirectionKeys

class DialogInfoCocktail : BaseDialogFragment<DialogCocktailInfoBinding>() {

    private lateinit var cocktail: Cocktail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cocktail = requireArguments().get(DirectionKeys.COCKTAIL.name) as Cocktail
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        setTitle(binding.cocktailTitle)
        setDescription(binding.cocktailDescription)
        return AlertDialog.Builder(baseActivity()).setView(binding.root).create()
    }

    private fun setTitle(textView: TextView) {
        textView.text = cocktail.name
    }

    private fun setDescription(textView: TextView) {
        textView.text = getDescription()
    }

    private fun getDescription(): String {
        val type = cocktail.type
        val method = cocktail.method
        val garnish = cocktail.garnish
        val note = cocktail.note
        val description = StringBuilder()
        if (type.trim().isNotEmpty() && type.trim() != "-") {
            description.append("Тип коктейля: $type")
        }
        if (method.trim().isNotEmpty() && method.trim() != "-") {
            description.append("\n\nМетод приготовления: $method")
        }
        if (garnish.trim().isNotEmpty() && garnish.trim() != "-") {
            description.append("\n\nУкрашение: $garnish")
        }
        if (note.trim().isNotEmpty() && note.trim() != "-") {
            description.append("\n\nПримечание: $note")
        }
        return description.toString()
    }

    override fun inflate(inflater: LayoutInflater): DialogCocktailInfoBinding {
        return DialogCocktailInfoBinding.inflate(inflater)
    }

}