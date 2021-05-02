package lk.game.cocktails.fragments.game.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import lk.game.cocktails.databinding.ItemIngredientBinding
import lk.game.cocktails.fragments.game.GameItemChangeMapper
import lk.game.cocktails.fragments.game.GameItemState
import lk.game.cocktails.retrofit.data.Ingredient

class GameRecyclerViewAdapter(
    private val values: List<Ingredient>,
    private val checkers: MutableLiveData<MutableList<GameItemState>>
) : RecyclerView.Adapter<GameRecyclerViewAdapter.GameViewHolder>() {

    private val colorMapper: GameItemChangeMapper = GameItemChangeMapper()

    class GameViewHolder(val binding: ItemIngredientBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = ItemIngredientBinding.inflate(inflater, parent, false)
        return GameViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.binding.textView.text = values[position].name
        colorMapper.set(checkers.value!![position], holder.binding.textView)
        holder.binding.textView.setOnClickListener {
            if (checkers.value!![position] == GameItemState.SELECTED) { //TODO
                checkers.value!![position] = GameItemState.CLEAR
            } else {
                checkers.value!![position] = GameItemState.SELECTED
            }
            colorMapper.set(checkers.value!![position], holder.binding.textView)
        }
    }

    override fun getItemCount() = values.size
}