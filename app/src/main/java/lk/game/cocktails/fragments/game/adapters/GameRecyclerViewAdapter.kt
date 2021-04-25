package lk.game.cocktails.fragments.game.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import lk.game.cocktails.R
import lk.game.cocktails.databinding.ItemIngredientBinding
import lk.game.cocktails.retrofit.data.Ingredient

class GameRecyclerViewAdapter(
    private val values: List<Ingredient>,
    private val checkers: MutableLiveData<MutableList<Boolean>>
) :
    RecyclerView.Adapter<GameRecyclerViewAdapter.GameViewHolder>() {

//    private var checkers: MutableList<Boolean> = mutableListOf()
//
//    init {
//        values.forEach { _ -> checkers.add(false) }
//    }

    class GameViewHolder(val binding: ItemIngredientBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = ItemIngredientBinding.inflate(inflater, parent, false)
        return GameViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.binding.textView.text = values[position].name
        changeColor(holder.binding.textView, position)
        holder.binding.textView.setOnClickListener {
            checkers.value!![position] = !checkers.value!![position]
            changeColor(it, position)
        }
    }

    private fun changeColor(view: View, position: Int) {
        val res = if (checkers.value!![position]) R.color.focus_of_attention else R.color.app_card
        val color = ContextCompat.getColor(view.context, res)
        view.setBackgroundColor(color)
    }

    override fun getItemCount() = values.size
}