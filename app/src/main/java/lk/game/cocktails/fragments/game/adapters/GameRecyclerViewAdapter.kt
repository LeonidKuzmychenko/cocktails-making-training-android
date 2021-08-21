package lk.game.cocktails.fragments.game.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import lk.game.cocktails.databinding.ItemIngredientBinding
import lk.game.cocktails.fragments.game.GameViewModel
import lk.game.cocktails.fragments.game.observers.result.GameResultAdapterObserver
import lk.game.cocktails.fragments.game.services.CountIngredientsService
import lk.game.cocktails.fragments.game.services.IngredientsStateService
import lk.game.cocktails.retrofit.data.Ingredient

class GameRecyclerViewAdapter(
    private val owner: LifecycleOwner,
    private val viewModel: GameViewModel,
    private val colorMapper: GameItemChangeMapper,
    private val ingredientsCountService: CountIngredientsService,
    private val values: List<Ingredient>
) : RecyclerView.Adapter<GameRecyclerViewAdapter.GameViewHolder>() {

    class GameViewHolder(val binding: ItemIngredientBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = ItemIngredientBinding.inflate(inflater, parent, false)
        return GameViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val textView = holder.binding.textView
        textView.text = values[position].name
        colorMapper.set(viewModel.checkers.value!![position], textView)
        val stateService = IngredientsStateService(viewModel, textView, colorMapper, position)
        viewModel.result.observe(
            owner,
            GameResultAdapterObserver(ingredientsCountService, stateService, textView)
        )
    }

    override fun getItemCount() = values.size
}