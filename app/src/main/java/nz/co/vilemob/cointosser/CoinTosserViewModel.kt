package nz.co.vilemob.cointosser

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CoinTosserViewModel : ViewModel() {

    private val events = MutableSharedFlow<CoinTosserEvent>()
    private val state = MutableStateFlow(CoinTosserState())

    val liveState: LiveData<CoinTosserState> = state.asLiveData()

    private val reactor = CoinTosserReactor()

    init {

        viewModelScope.launch {

            launch {
                events.mapNotNull(reactor::react)
                        .collect { launch { events.emit(it) } }
            }

            launch {
                events.map { reduce(state.value, it) }
                        .collect(state::emit)
            }
        }
    }

    fun onToss() {
        viewModelScope.launch { events.emit(CoinTosserEvent.Toss) }
    }
}
