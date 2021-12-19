package com.android.paysafe.ui.stores

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.paysafe.databinding.ItemStoreBinding
import com.android.paysafe.model.Store

class StoreListAdapter(): ListAdapter<Store, StoreListAdapter.StoreViewHolder>(DIFF_CALLBACK) {


    class StoreViewHolder(binding: ItemStoreBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(store: Store) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        return StoreViewHolder(ItemStoreBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Store>() {

    override fun areItemsTheSame(oldItem: Store, newItem: Store): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Store, newItem: Store): Boolean {
        return oldItem == newItem
    }

}
