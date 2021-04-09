package nz.co.vilemob.cointosser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_coin_tosser.*

class CoinTosserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_tosser)

        val viewModel = ViewModelProvider(this).get(CoinTosserViewModel::class.java)
        viewModel.liveState.observe(this, Observer {
            it?.apply {
                statusTextView.text = when {
                    isTossing -> getString(R.string.tossing)
                    isHeads -> getString(R.string.heads)
                    else -> getString(R.string.tails)
                }
                tossButton.isEnabled = !isTossing
            }
        })

        tossButton.setOnClickListener { viewModel.onToss() }
    }
}
