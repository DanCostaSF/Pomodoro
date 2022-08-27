package br.com.android.pomodoro

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimerViewModel : ViewModel() {

    val state = MutableLiveData<TimerState>()
    private var timer: CountDownTimer? = null
    private var secondsRemaining: Long = BASE_TIME_LENGHT
    private var timerLengthSeconds: Long = BASE_TIME_LENGHT

    fun handleIntent(intent: TimerIntent){
        when(intent){
            TimerIntent.Pause -> pauseTimer()
            TimerIntent.Start -> startTimer()
            TimerIntent.Cancel -> cancelTimer()
        }
    }

    private fun pauseTimer(){

    }

    private fun startTimer(){
        cancelTimer()
        timer = object : CountDownTimer(
            secondsRemaining * COUNT_DOWN_INTERVAL,
            COUNT_DOWN_INTERVAL.toLong()
        ){
            override fun onTick(millisUntilFinished: Long) {
                val minutes = millisUntilFinished / MILLIS_IN_FUTURE
                val finishIn = (millisUntilFinished - minutes * MILLIS_IN_FUTURE) / COUNT_DOWN_INTERVAL

                secondsRemaining = millisUntilFinished / COUNT_DOWN_INTERVAL

                state.value = TimerState.OnTick(
                    timeValue = secondsRemaining.toInt(),
                    time = "${minutes.toString().padStart(2,'0')}:" +
                            finishIn.toString().padStart(2,'0')
                )
            }

            override fun onFinish() {
                cancelTimer()
                timerLengthSeconds = BASE_TIME_LENGHT
                secondsRemaining = timerLengthSeconds
                //TODO: Criar estado de finalizado e resetar texto
                state.value = TimerState.OnTick(BASE_TIME_LENGHT.toInt(),"25:00")
            }
        }
        timer?.start()
    }

    private fun cancelTimer(){
        timer?.cancel()
    }

    companion object{
//        const val BASE_TIME_LENGHT = 1500L
        const val BASE_TIME_LENGHT = 30L
        const val REST_TIME_LENGHT = 300L
        const val MILLIS_IN_FUTURE = 60000
        const val COUNT_DOWN_INTERVAL = 1000
    }
}