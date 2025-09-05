package com.example.lab_week02_b

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        const val COLOR_KEY = "COLOR_KEY"
    }

    private val submitButton: Button
        get() = findViewById(R.id.submit_button)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        submitButton.setOnClickListener {
            val colorCode =
                findViewById<TextInputEditText>(R.id.color_code_input_field).text?.toString()?.trim()
                    ?: ""

            when {
                colorCode.isEmpty() -> {
                    Toast.makeText(
                        this,
                        getString(R.string.color_code_input_empty),
                        Toast.LENGTH_LONG
                    ).show()
                }
                colorCode.length != 6 -> {
                    Toast.makeText(
                        this,
                        getString(R.string.color_code_input_wrong_length),
                        Toast.LENGTH_LONG
                    ).show()
                }
                !colorCode.matches(Regex("^[0-9A-Fa-f]{6}$")) -> {
                    Toast.makeText(
                        this,
                        getString(R.string.color_code_input_invalid),
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {
                    val resultIntent = Intent(this, ResultActivity::class.java).apply {
                        putExtra(COLOR_KEY, colorCode.uppercase())
                    }
                    startActivity(resultIntent)
                }
            }
        }
    }
}
