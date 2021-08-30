package lk.game.cocktails.fragments.library.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import lk.game.cocktails.R
import lk.game.cocktails.databinding.ItemLibraryBinding
import lk.game.cocktails.retrofit.data.CocktailShort
import lk.game.cocktails.statistics.services.SharedPrefStatisticService

class LibraryRecyclerViewAdapter(
    private val values: List<CocktailShort>,
    private val serverName: String,
    private val sharedPrefStatistic: SharedPrefStatisticService
) :
    RecyclerView.Adapter<LibraryRecyclerViewAdapter.LibraryViewHolder>() {

    class LibraryViewHolder(val binding: ItemLibraryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = ItemLibraryBinding.inflate(inflater, parent, false)
        return LibraryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LibraryViewHolder, position: Int) {
        val cocktail = values[position]
        holder.binding.libraryName.text = cocktail.name
        val image = holder.binding.libraryImage
        Glide.with(image.context)
            .load(serverName + cocktail.photo)
            .centerCrop()
            .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
            .error(ContextCompat.getDrawable(image.context, R.drawable.error))
            .into(image)
        holder.binding.libraryCard.setOnClickListener {
            sharedPrefStatistic.addLibraryResult(cocktail.name)
        }

    }

    override fun getItemCount() = values.size
}