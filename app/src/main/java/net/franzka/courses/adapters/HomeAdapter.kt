package net.franzka.courses.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import net.franzka.courses.adapters.HomeAdapter.*
import net.franzka.courses.databinding.ListItemHomeBinding
import net.franzka.courses.models.Home

class HomeAdapter(private val clickListener: HomeClickListener) : ListAdapter<Home, ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).homeId?.toLong() ?: 0
    }

    class ViewHolder private constructor(
        val binding: ListItemHomeBinding,
        private val parent: ViewGroup
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(home: Home, clickListener: HomeClickListener) {
            binding.apply {
                this.home = home
                this.clickListener = clickListener
                executePendingBindings()
            }
        }


        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemHomeBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding, parent)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Home>() {

        override fun areItemsTheSame(oldItem: Home, newItem: Home): Boolean {
            return oldItem.homeId == newItem.homeId
        }


        override fun areContentsTheSame(oldItem: Home, newItem: Home): Boolean {
            return oldItem == newItem
        }

    }
}

class HomeClickListener(val clickListener: (home: Home) -> Unit) {
    fun onClick(home: Home) = clickListener(home)
}
