package lk.game.cocktails.fragments.game.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import lk.game.cocktails.R
import lk.game.cocktails.databinding.ItemIngredientBinding
import lk.game.cocktails.retrofit.data.Ingredient

class GameRecyclerViewAdapter(private val values: List<Ingredient>) :
    RecyclerView.Adapter<GameRecyclerViewAdapter.GameViewHolder>() {

    private var checkers: MutableList<Boolean> = mutableListOf()

    init {
        values.forEach { _ -> checkers.add(false) }
    }

    class GameViewHolder(val binding: ItemIngredientBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = ItemIngredientBinding.inflate(inflater, parent, false)
        return GameViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.binding.textView.text = values[position].name
        holder.binding.textView.setOnClickListener {
            val res = if (checkers[position]) R.color.app_card else R.color.focus_of_attention
            val color = ContextCompat.getColor(it.context, res)
            it.setBackgroundColor(color)
            checkers[position] = !checkers[position]
        }
    }

    override fun getItemCount() = values.size
}