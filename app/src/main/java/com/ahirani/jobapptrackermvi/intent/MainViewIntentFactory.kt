package com.ahirani.jobapptrackermvi.intent

import com.ahirani.jobapptrackermvi.model.JobAppModel
import com.ahirani.jobapptrackermvi.model.JobAppModelStore
import com.ahirani.jobapptrackermvi.view.MainViewEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
object MainViewIntentFactory : IntentFactory<MainViewEvent> {

    override suspend fun process(viewEvent: MainViewEvent) {
        JobAppModelStore.process(toIntent(viewEvent))
    }

    private fun toIntent(viewEvent: MainViewEvent): Intent<JobAppModel> =
        when (viewEvent) {
            MainViewEvent.LoveItClick -> AddHeart()
            MainViewEvent.ThumbsUpClick -> AddThumb()
        }
}