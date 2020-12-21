package com.ahirani.jobapptrackermvi.intent

interface Intent<T> {
    fun reduce(oldState: T): T
}