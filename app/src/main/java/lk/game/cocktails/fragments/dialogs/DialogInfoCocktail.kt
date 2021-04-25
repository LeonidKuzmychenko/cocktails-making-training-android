package lk.game.cocktails.fragments.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import lk.game.cocktails.databinding.DialogCocktailInfoBinding
import lk.game.cocktails.retrofit.data.Cocktail


class DialogInfoCocktail : DialogFragment() {

    private lateinit var binding: DialogCocktailInfoBinding
    private lateinit var cocktail: Cocktail

    override fun onAttach(context: Context) {
        super.onAttach(context)
        binding = DialogCocktailInfoBinding.inflate(LayoutInflater.from(context))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cocktail = requireArguments().get("cocktail") as Cocktail
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, sis: Bundle?): View {
        binding = DialogCocktailInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity).setView(binding.root)
        setTitle(binding.cocktailTitle)
        setImage(binding.cocktailImage)
        setDescription(binding.cocktailDescription)
        return builder.create()
    }

    private fun setImage(imageView: ImageView) {
        val imagePath = "http://cocktails-making-training.herokuapp.com" + cocktail.photo
        Glide.with(requireContext())
            .load(imagePath)
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

}