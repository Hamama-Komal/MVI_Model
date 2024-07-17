package com.example.mvimodelcounterapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.mvimodelcounterapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: CounterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel = ViewModelProvider(this).get(CounterViewModel::class.java)

        binding.btnIncrement.setOnClickListener {
            viewModel.onEvent(CounterEvent.Increment)
        }

        binding.btnDecrement.setOnClickListener {
            viewModel.onEvent(CounterEvent.Decrement)
        }

        // convert this state into LiveData
        val state = viewModel.state.asLiveData()
        state.observe(this){state ->
            val count = state.count
            binding.textCounter.text = count.toString()
        }
    }

}