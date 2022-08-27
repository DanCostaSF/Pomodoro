package br.com.android.pomodoro

sealed class TimerState{

    data class OnTick(
        val timeValue: Int,
        val time: String
        ): TimerState()
}
