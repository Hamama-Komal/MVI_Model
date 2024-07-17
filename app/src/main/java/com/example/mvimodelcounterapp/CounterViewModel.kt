package com.example.mvimodelcounterapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class CounterViewModel : ViewModel(){

    // Here we use LiveData or Flow
    var state = MutableStateFlow(CounterState())

    private set

    fun onEvent(event: CounterEvent){
        when(event){
            CounterEvent.Decrement -> state.update {
                if(state.value.count == 0)
                    return  // No need to show negative values
                it.copy(
                    count = it.count - 1
                )
            }
            CounterEvent.Increment -> state.update {
                it.copy(
                    count = it.count + 1
                )
            }
        }
    }
}