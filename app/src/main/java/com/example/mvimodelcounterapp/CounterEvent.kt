package com.example.mvimodelcounterapp

// Event / Intent
sealed class CounterEvent {
    data object Increment : CounterEvent()
    data object Decrement : CounterEvent()
}