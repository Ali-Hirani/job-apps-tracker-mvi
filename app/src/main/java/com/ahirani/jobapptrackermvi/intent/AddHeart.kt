package com.ahirani.jobapptrackermvi.intent

import com.ahirani.jobapptrackermvi.model.JobAppModel

class AddHeart() : Intent<JobAppModel> {

    override fun reduce(oldState: JobAppModel): JobAppModel =
        oldState.copy(hearts = oldState.hearts + 1)
}