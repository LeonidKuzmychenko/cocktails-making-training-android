package lk.game.cocktails.fragments.game.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import lk.game.cocktails.databinding.ItemIngredientBinding
import lk.game.cocktails.fragments.game.GameFragment
import lk.game.cocktails.fragments.game.observers.GameResultAdapterObserver
import lk.game.cocktails.retrofit.data.Ingredient

class GameRecyclerViewAdapter(
    private val values: List<Ingredient>,
    private val fragment: GameFragment
) : RecyclerView.Adapter<GameRecyclerViewAdapter.GameViewHolder>() {

    private val colorMapper: GameItemChangeMapper = GameItemChangeMapper()

    class GameViewHolder(val binding: ItemIngredientBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = ItemIngredientBinding.inflate(inflater, parent, false)
        return GameViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val checkers = fragment.viewModel.checkers
        val textView = holder.binding.textView
        textView.text = values[position].name
        colorMapper.set(checkers.value!![position], textView)
        fragment.viewModel.result.observe(
            fragment.viewLifecycleOwner,
            GameResultAdapterObserver(fragment, textView, position)
        )
    }

    override fun getItemCount() = values.size
}