package nz.co.vilemob.cointosser

import io.reactivex.Single
import nz.co.vilemob.rxmodel.StateEventModel
import java.util.concurrent.TimeUnit

class CoinTosserModel
    : StateEventModel<CoinTosserState, CoinTosserEvent>(
        CoinTosserState(),
        CoinTosserReducer
) {
    private val tossEventsObservable = eventObservable
            .ofType(CoinTosserEvent.Toss::class.java)

    private val tossSideEffect = Single
            .timer(1, TimeUnit.SECONDS)
            .map {
                when {
                    Math.random() < .5 -> CoinTosserEvent.Heads
                    else -> CoinTosserEvent.Tails
                }
            }

    override fun subscribe() = publish(
            tossEventsObservable.flatMapSingle { tossSideEffect }
    )
}
