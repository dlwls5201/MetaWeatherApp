package com.tistory.blackjin.metaweatherapp.presenter.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tistory.blackjin.metaweatherapp.R
import com.tistory.blackjin.metaweatherapp.base.BaseBindingViewHolder
import com.tistory.blackjin.metaweatherapp.base.BaseViewHolder
import com.tistory.blackjin.metaweatherapp.databinding.ItemWeatherBinding
import com.tistory.blackjin.metaweatherapp.databinding.ItemWeatherHeaderBinding
import com.tistory.blackjin.metaweatherapp.presenter.model.LocalWeatherItem

class WeatherAdapter : RecyclerView.Adapter<BaseViewHolder<LocalWeatherItem>>() {

    private val items = mutableListOf<LocalWeatherItem?>()

    fun replaceAll(items: List<LocalWeatherItem?>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int) = getViewType(position).ordinal

    private fun getViewType(position: Int): ViewType {
        return if (position < headerCount) {
            ViewType.HEADER
        } else {
            ViewType.ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<LocalWeatherItem> {
        return when (ViewType.getViewType(viewType)) {
            ViewType.HEADER -> WeatherHeaderViewHolder(parent)
            ViewType.ITEM -> WeatherItemViewHolder(parent)
        }
    }

    override fun getItemCount() = if (items.size > 0) items.size + headerCount else 0

    override fun onBindViewHolder(holder: BaseViewHolder<LocalWeatherItem>, position: Int) {
        when (getViewType(position)) {
            ViewType.HEADER -> {
            }
            ViewType.ITEM -> holder.bind(getPositionItem(position))
        }
    }

    private fun getPositionItem(position: Int): LocalWeatherItem {
        return items[position - headerCount]!!
    }

    private val headerCount = 1

    enum class ViewType {
        HEADER, ITEM;

        companion object {
            fun getViewType(value: Int) = values()[value]
        }
    }

    class WeatherHeaderViewHolder(parent: ViewGroup) :
        BaseBindingViewHolder<ItemWeatherHeaderBinding, LocalWeatherItem>(parent, R.layout.item_weather_header) {
        override fun bind(item: LocalWeatherItem) {
            // no-op
        }
    }

    class WeatherItemViewHolder(parent: ViewGroup) :
        BaseBindingViewHolder<ItemWeatherBinding, LocalWeatherItem>(parent, R.layout.item_weather) {
        override fun bind(item: LocalWeatherItem) {
            try {
                binding.run {
                    binding.localWeatherItem = item
                    executePendingBindings()
                }
                itemView.visibility = View.VISIBLE
            } catch (e: Exception) {
                e.printStackTrace()
                itemView.visibility = View.GONE
            }
        }
    }
}