package nz.co.vilemob.cointosser

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_coin_tosser.*

class CoinTosserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_tosser)

        val viewModel = ViewModelProviders.of(this).get(CoinTosserViewModel::class.java)
        viewModel.liveState.observe(this, Observer {
            it?.apply {
                statusTextView.text = when{
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
