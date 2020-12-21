package com.ahirani.jobapptrackermvi.intent

interface IntentFactory<E> {
    // TODO: Understand why it's a suspend function
    suspend fun process(viewEvent: E)
}
