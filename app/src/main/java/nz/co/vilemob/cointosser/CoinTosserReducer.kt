package nz.co.vilemob.cointosser

import nz.co.vilemob.rxmodel.Reducer

object CoinTosserReducer : Reducer<CoinTosserState, CoinTosserEvent> {
    override fun apply(
            state: CoinTosserState,
            event: CoinTosserEvent
    ) = when (event) {
        CoinTosserEvent.Toss -> state.copy(
                isTossing = true
        )
        CoinTosserEvent.Heads -> state.copy(
                isTossing = false,
                isHeads = true
        )
        CoinTosserEvent.Tails -> state.copy(
                isTossing = false,
                isHeads = false
        )
    }
}
