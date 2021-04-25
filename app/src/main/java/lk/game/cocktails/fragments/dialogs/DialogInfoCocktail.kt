package lk.game.cocktails.fragments.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import lk.game.cocktails.base.BaseDialogFragment
import lk.game.cocktails.databinding.DialogCocktailInfoBinding
import lk.game.cocktails.retrofit.data.Cocktail
import lk.game.cocktails.utils.DirectionKeys

class DialogInfoCocktail : BaseDialogFragment<DialogCocktailInfoBinding>() {

    private lateinit var cocktail: Cocktail
    private lateinit var serverName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cocktail = requireArguments().get(DirectionKeys.COCKTAIL.name) as Cocktail
        serverName = requireArguments().get(DirectionKeys.SERVER_NAME.name) as String
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        setTitle(binding.cocktailTitle)
        setImage(binding.cocktailImage)
        setDescription(binding.cocktailDescription)
        return AlertDialog.Builder(baseActivity()).setView(binding.root).create()
    }

    private fun setImage(imageView: ImageView) {
        Glide.with(baseActivity())
            .load(serverName + cocktail.photo)
            .fitCenter()
            .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE))
            .into(imageView)
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