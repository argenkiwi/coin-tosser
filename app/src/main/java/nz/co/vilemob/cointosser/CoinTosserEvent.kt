package nz.co.vilemob.cointosser

sealed class CoinTosserEvent {
    object Toss : CoinTosserEvent()
    object Heads : CoinTosserEvent()
    object Tails : CoinTosserEvent()
}
