package lk.game.cocktails.fragments.game.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import lk.game.cocktails.databinding.ItemIngredientBinding
import lk.game.cocktails.fragments.game.data.GameItemState
import lk.game.cocktails.fragments.game.observers.result.GameResultAdapterObserver
import lk.game.cocktails.retrofit.data.Cocktail
import lk.game.cocktails.retrofit.data.Ingredient

class GameRecyclerViewAdapter(
    private val owner: LifecycleOwner,
    private val cocktail: MutableLiveData<Cocktail>,
    private val checkers: MutableLiveData<MutableList<GameItemState>>,
    private val result: MutableLiveData<Boolean>,
    private val values: List<Ingredient>
) : RecyclerView.Adapter<GameRecyclerViewAdapter.GameViewHolder>() {

    private val colorMapper: GameItemChangeMapper = GameItemChangeMapper()

    class GameViewHolder(val binding: ItemIngredientBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = ItemIngredientBinding.inflate(inflater, parent, false)
        return GameViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val textView = holder.binding.textView
        textView.text = values[position].name
        colorMapper.set(checkers.value!![position], textView)
        result.observe(owner, GameResultAdapterObserver(cocktail, checkers, textView, position))
    }

    override fun getItemCount() = values.size
}