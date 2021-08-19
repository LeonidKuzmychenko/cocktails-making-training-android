package lk.game.cocktails.fragments.mode.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import lk.game.cocktails.databinding.ItemModeBinding
import lk.game.cocktails.fragments.mode.ModeFragmentDirections
import lk.game.cocktails.retrofit.data.Mode

class ModeRecyclerViewAdapter(private val values: List<Mode>) :
    RecyclerView.Adapter<ModeRecyclerViewAdapter.ModeViewHolder>() {

    class ModeViewHolder(val binding: ItemModeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = ItemModeBinding.inflate(inflater, parent, false)
        return ModeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ModeViewHolder, position: Int) {
        holder.binding.modeName.text = values[position].name
        holder.binding.modeCard.setOnClickListener {
            if (position == 1) {
                val action = ModeFragmentDirections.actionModeFragmentToGameFragment()
                Navigation.findNavController(holder.itemView).navigate(action)
            } else {
                Toast.makeText(holder.itemView.context, "LIBRARY", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount() = values.size
}