package nz.co.vilemob.cointosser

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import io.reactivex.BackpressureStrategy

class CoinTosserViewModel : ViewModel() {
    private val model = CoinTosserModel()
    private val disposable = model.subscribe()

    val liveState: LiveData<CoinTosserState> =
            LiveDataReactiveStreams.fromPublisher(
                    model.stateObservable
                            .toFlowable(BackpressureStrategy.LATEST)
            )

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

    fun onToss() {
        model.publish(CoinTosserEvent.Toss)
    }
}
