package com.tistory.blackjin.metaweatherapp.domain.provider

import io.reactivex.Scheduler

interface SchedulersProvider {
    fun io(): Scheduler
    fun ui(): Scheduler
}
