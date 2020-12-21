package com.ahirani.jobapptrackermvi.view

import android.view.View
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

@ExperimentalCoroutinesApi
fun View.clicks(): Flow<Unit> = callbackFlow {
    val listener = View.OnClickListener { offer(Unit) } // Every time the user clicks, we offer a unit on the Channel
    setOnClickListener(listener)
    awaitClose {
        setOnClickListener(null)
    }
}