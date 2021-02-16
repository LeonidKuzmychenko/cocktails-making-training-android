package lk.game.cocktails.fragments.game.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import lk.game.cocktails.databinding.ItemIngredientBinding
import lk.game.cocktails.retrofit.data.Ingredient

class GameRecyclerViewAdapter(private val values: List<Ingredient>) :
    RecyclerView.Adapter<GameRecyclerViewAdapter.GameViewHolder>() {

    class GameViewHolder(val binding: ItemIngredientBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = ItemIngredientBinding.inflate(inflater, parent, false)
        return GameViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.binding.textView.text = values[position].name
    }

    override fun getItemCount() = values.size
}