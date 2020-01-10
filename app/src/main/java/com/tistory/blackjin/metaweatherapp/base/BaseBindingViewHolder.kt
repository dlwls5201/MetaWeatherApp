package com.tistory.blackjin.metaweatherapp.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseBindingViewHolder<out B : ViewDataBinding, ITEM>(
    parent: ViewGroup,
    @LayoutRes layoutRes: Int
) : BaseViewHolder<ITEM>(
    LayoutInflater.from(parent.context)
        .inflate(layoutRes, parent, false)
) {

    protected val binding: B = DataBindingUtil.bind(itemView)!!
}