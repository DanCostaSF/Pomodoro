package br.com.android.pomodoro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import br.com.android.pomodoro.databinding.FragmentTimerBinding

class TimerFragment : Fragment() {

    private lateinit var viewBinding: FragmentTimerBinding
    private lateinit var viewModel: TimerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentTimerBinding.inflate(inflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[TimerViewModel::class.java]

        setListeners()
        setObserver()
        //TODO: Refatorar quando passar maximo
        viewBinding.circularProgressIndicator.max = 30
        viewBinding.circularProgressIndicator.progress = 0
    }

    private fun setObserver(){
        viewModel.state.observe(viewLifecycleOwner){ state ->
            when(state){
                is TimerState.OnTick -> {
                    updateCountDown(state.timeValue)
                    viewBinding.txtTimer.text = state.time
                }
            }
        }
    }

    private fun updateCountDown(time: Int){
        viewBinding.circularProgressIndicator.setProgressCompat(time,true)
    }

    private fun setListeners() = viewBinding.run{
        btnStart.setOnClickListener {
            viewModel.handleIntent(TimerIntent.Start)
        }
    }
}