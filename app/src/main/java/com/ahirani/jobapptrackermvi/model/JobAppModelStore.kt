package com.ahirani.jobapptrackermvi.model

import com.ahirani.jobapptrackermvi.common.FlowModelStore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
object JobAppModelStore : FlowModelStore<JobAppModel>(JobAppModel(0, 0))