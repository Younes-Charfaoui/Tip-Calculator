package com.mxcsyounes.tipcalculator

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var currentTip = 0.15
    private var currentBill = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addListenersToViews()
    }

    private fun addListenersToViews() {
        tipSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                handleTipChanges(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

        billEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(string: Editable) {
                if (string.isNotBlank())
                    handleBillChanges(string.toString().toDouble())
                else handleBillChanges(0.0)
            }

            override fun beforeTextChanged(
                string: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(string: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }

    private fun handleTipChanges(tip: Int) {
        currentTip = tip / 100.0
        tipPercentageValueTextView.text = getString(R.string.percentageString, tip.toString())
        updateBillAndTotalAmount()
    }

    private fun handleBillChanges(bill: Double) {
        if (bill >= 0) currentBill = bill
        updateBillAndTotalAmount()
    }

    private fun updateBillAndTotalAmount() {
        val tipAmount = currentBill * currentTip
        val totalAmount = currentBill + tipAmount
        tipAmountValueTextView.text = getString(R.string.amountString, tipAmount)
        totalAmountValueTextView.text = getString(R.string.amountString, totalAmount)
    }

}
