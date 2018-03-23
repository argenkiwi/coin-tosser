package nz.co.vilemob.cointosser

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.ViewModel
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
