package com.tistory.blackjin.metaweatherapp.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<ITEM>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(item: ITEM)
}