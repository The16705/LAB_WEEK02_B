package com.example.lab_week02_b

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val colorCode = intent.getStringExtra(MainActivity.COLOR_KEY)
        val backgroundScreen = findViewById<ConstraintLayout>(R.id.background_screen)
        val resultMessage = findViewById<TextView>(R.id.color_code_result_message)

        if (!colorCode.isNullOrEmpty()) {
            try {
                backgroundScreen.setBackgroundColor(Color.parseColor("#$colorCode"))

                resultMessage.text = getString(
                    R.string.color_code_result_message,
                    colorCode.uppercase()
                )

                val returnIntent = Intent().apply {
                    putExtra(MainActivity.COLOR_KEY, colorCode.uppercase())
                    putExtra(MainActivity.ERROR_KEY, false)
                }
                setResult(Activity.RESULT_OK, returnIntent)

            } catch (ex: IllegalArgumentException) {
                val errorIntent = Intent().apply {
                    putExtra(MainActivity.ERROR_KEY, true)
                }
                setResult(Activity.RESULT_OK, errorIntent)
            }
        }
    }
}
