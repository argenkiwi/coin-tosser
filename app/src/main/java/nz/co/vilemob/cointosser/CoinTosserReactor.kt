package nz.co.vilemob.cointosser

import kotlinx.coroutines.delay

class CoinTosserReactor() {

    suspend fun react(
            event: CoinTosserEvent
    ): CoinTosserEvent? = when (event) {
        is CoinTosserEvent.Toss -> {
            delay(1000)
            when {
                Math.random() < .5 -> CoinTosserEvent.Heads
                else -> CoinTosserEvent.Tails
            }
        }
        else -> null
    }
}
