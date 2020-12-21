package com.ahirani.jobapptrackermvi.view

sealed class MainViewEvent {
    object LoveItClick : MainViewEvent()
    object ThumbsUpClick : MainViewEvent()
}