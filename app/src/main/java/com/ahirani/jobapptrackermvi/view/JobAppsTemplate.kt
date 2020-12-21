package com.ahirani.jobapptrackermvi.view

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ahirani.jobapptrackermvi.R
import com.ahirani.jobapptrackermvi.common.ViewEventFlow
import com.ahirani.jobapptrackermvi.intent.MainViewIntentFactory
import com.ahirani.jobapptrackermvi.model.JobAppModel
import com.ahirani.jobapptrackermvi.model.JobAppModelStore
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

@FlowPreview
@ExperimentalCoroutinesApi
class JobAppsTemplate : AppCompatActivity(), ViewEventFlow<MainViewEvent> {
    private val scope: CoroutineScope = MainScope()

    private lateinit var counterTextView: TextView
    private lateinit var heartButton: Button
    private lateinit var thumbButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        counterTextView = findViewById(R.id.counterTextView)
        heartButton = findViewById(R.id.heartButton)
        thumbButton = findViewById(R.id.thumbButton)

        viewEvents()
            .onEach { event -> MainViewIntentFactory.process(event) } // Triggered whenever an event comes in
            .launchIn(scope)

        JobAppModelStore
            .modelState()
            .forCounterTextView()
            .launchIn(scope)
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }

    override fun viewEvents(): Flow<MainViewEvent> {
        val flows = listOf(
            heartButton.clicks().map { MainViewEvent.LoveItClick },
            thumbButton.clicks().map { MainViewEvent.ThumbsUpClick }
        )

        // asFlow - Transform into a flow of flows
        // flattenMerge - Flatten into singe flow
        // Note: Pass in flows.size for concurrency so that it handles
        // all flows at the same time as opposed to sequentially
        return flows.asFlow().flattenMerge(flows.size)
    }

    private fun Flow<JobAppModel>.forCounterTextView() =
        onEach { model ->
            counterTextView.text = resources.getString(R.string.upvotes, model.hearts, model.thumbs)
        }

}
