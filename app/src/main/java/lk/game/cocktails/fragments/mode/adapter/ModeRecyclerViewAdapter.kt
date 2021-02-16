package lk.game.cocktails.fragments.mode.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import lk.game.cocktails.databinding.ItemChooseModeBinding

class ModeRecyclerViewAdapter(private val values: List<String>) :
    RecyclerView.Adapter<ModeRecyclerViewAdapter.ModeViewHolder>() {

    class ModeViewHolder(val binding: ItemChooseModeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = ItemChooseModeBinding.inflate(inflater, parent, false)
        return ModeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ModeViewHolder, position: Int) {
        holder.binding.textView3.text = values[position]
    }

    override fun getItemCount() = values.size
}