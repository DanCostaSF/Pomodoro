package br.com.android.pomodoro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import br.com.android.pomodoro.databinding.FragmentTimerBinding


class TimerFragment : Fragment() {

    private lateinit var viewBinding: FragmentTimerBinding
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

}