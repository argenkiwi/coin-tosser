package nz.co.vilemob.cointosser

fun reduce(
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
