package com.ahirani.jobapptrackermvi.common

import com.ahirani.jobapptrackermvi.intent.Intent
import com.ahirani.jobapptrackermvi.model.ModelStore
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

@FlowPreview
@ExperimentalCoroutinesApi
open class FlowModelStore<S>(startingState: S): ModelStore<S> {
    private val scope = MainScope()
    private val intents = Channel<Intent<S>>()
    private val store = ConflatedBroadcastChannel(startingState)

    init {
        scope.launch {
            while (isActive) store.offer(intents.receive().reduce(store.value))
        }
    }

    override suspend fun process(intent: Intent<S>) {
        intents.send(intent)
    }

    override fun modelState(): Flow<S> {
        return store.asFlow()
    }

    fun close() {
        intents.close()
        store.close()
        scope.cancel()
    }
}