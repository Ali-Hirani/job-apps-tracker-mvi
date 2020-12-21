package com.ahirani.jobapptrackermvi.intent

import com.ahirani.jobapptrackermvi.model.JobAppModel

class AddThumb() : Intent<JobAppModel> {

    override fun reduce(oldState: JobAppModel): JobAppModel =
        oldState.copy(thumbs = oldState.thumbs + 1)
}