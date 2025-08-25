package com.example.rgbcolor // Make sure this matches your actual package name

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var redChannelEditText: TextInputEditText
    private lateinit var greenChannelEditText: TextInputEditText
    private lateinit var blueChannelEditText: TextInputEditText
    private lateinit var mixColorButton: Button
    private lateinit var colorDisplayView: View

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Note: enableEdgeToEdge() and the WindowInsets listener from the original template
        // have been removed as they are not the primary focus of this specific lab activity.
        // If you need edge-to-edge, you can add it back, but ensure compatibility
        // with how views are accessed and laid out.
        setContentView(R.layout.activity_main)

        // Initialize views
        redChannelEditText = findViewById(R.id.redChannelEditText)
        greenChannelEditText = findViewById(R.id.greenChannelEditText)
        blueChannelEditText = findViewById(R.id.blueChannelEditText)
        mixColorButton = findViewById(R.id.mixColorButton)
        colorDisplayView = findViewById(R.id.colorDisplayView)

        mixColorButton.setOnClickListener {
            mixAndDisplayColor()
        }
    }

    private fun mixAndDisplayColor() {
        // Get text from EditTexts
        val redValue = redChannelEditText.text.toString().trim()
        val greenValue = greenChannelEditText.text.toString().trim()
        val blueValue = blueChannelEditText.text.toString().trim()1,

        // Validate input
        if (isValidHexInput(redValue) && isValidHexInput(greenValue) && isValidHexInput(blueValue)) {
            // Concatenate and form color string
            val colorString = "#$redValue$greenValue$blueValue"

            try {
                // Parse and set color
                val color = Color.parseColor(colorString)
                colorDisplayView.setBackgroundColor(color)
                // Clear any previous errors
                redChannelEditText.error = null
                greenChannelEditText.error = null
                blueChannelEditText.error = null
            } catch (e: IllegalArgumentException) {
                // This might happen if the string is somehow still invalid
                Toast.makeText(this, "Invalid color format", Toast.LENGTH_SHORT).show()
            }
        } else {
            // Set errors on invalid fields
            if (!isValidHexInput(redValue)) {
                redChannelEditText.error = "Enter 2 hex characters"
            } else {
                redChannelEditText.error = null // Clear error if this specific field is now valid
            }

            if (!isValidHexInput(greenValue)) {
                greenChannelEditText.error = "Enter 2 hex characters"
            } else {
                greenChannelEditText.error = null // Clear error if this specific field is now valid
            }

            if (!isValidHexInput(blueValue)) {
                blueChannelEditText.error = "Enter 2 hex characters"
            } else {
                blueChannelEditText.error = null // Clear error if this specific field is now valid
            }
            // Only show toast if at least one field is still invalid after individual checks
            if (!isValidHexInput(redValue) || !isValidHexInput(greenValue) || !isValidHexInput(blueValue)) {
                Toast.makeText(this, "Please enter valid 2-digit hex values for each channel.", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun isValidHexInput(input: String): Boolean {
        // Regex to check for exactly 2 hexadecimal characters (0-9, A-F, a-f)
        val hexPattern = Regex("^[0-9a-fA-F]{2}$")
        return input.matches(hexPattern)
    }
}
