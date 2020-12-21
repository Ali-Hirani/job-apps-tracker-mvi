package com.ahirani.jobapptrackermvi.common

import kotlinx.coroutines.flow.Flow

/**
 * Allows grouping all viewEvents from one view in a single Flow.
 */
interface ViewEventFlow<E> {
    fun viewEvents(): Flow<E>
}