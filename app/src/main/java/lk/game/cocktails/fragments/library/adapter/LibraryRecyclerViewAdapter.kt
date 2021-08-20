package lk.game.cocktails.fragments.library.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import lk.game.cocktails.databinding.ItemLibraryBinding
import lk.game.cocktails.retrofit.data.Mode

class LibraryRecyclerViewAdapter(private val values: List<Mode>) :
    RecyclerView.Adapter<LibraryRecyclerViewAdapter.LibraryViewHolder>() {

    class LibraryViewHolder(val binding: ItemLibraryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = ItemLibraryBinding.inflate(inflater, parent, false)
        return LibraryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LibraryViewHolder, position: Int) {
        holder.binding.libraryName.text = values[position].name
        holder.binding.libraryCard.setOnClickListener {

        }
    }

    override fun getItemCount() = values.size
}