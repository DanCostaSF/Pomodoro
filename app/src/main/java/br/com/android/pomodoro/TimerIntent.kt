package br.com.android.pomodoro

sealed class TimerIntent{

    object Start: TimerIntent()
    object Pause: TimerIntent()
    object Cancel: TimerIntent()
}
