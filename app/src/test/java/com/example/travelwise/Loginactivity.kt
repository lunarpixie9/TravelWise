package com.example.travelwise

package com.example.travelwise

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.travelwise.ui.home.HomeActivity
import com.google.android.material.textfield.TextInputEditText

/**
 * An activity for user login. It presents fields for email and password
 * and a button to initiate login.
 */
class LoginActivity : AppCompatActivity() {

    // Declare UI elements
    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var loginButton: Button
    private lateinit var signupText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the content view to your new login layout
        setContentView(R.layout.activity_login)

        // Initialize UI elements by finding them in the layout
        emailEditText = findViewById(R.id.et_email)
        passwordEditText = findViewById(R.id.et_password)
        loginButton = findViewById(R.id.btn_login)
        signupText = findViewById(R.id.tv_signup)

        // Set a click listener for the login button
        loginButton.setOnClickListener {
            handleLogin()
        }

        // Set a click listener for the "Sign Up" text
        signupText.setOnClickListener {
            // For now, show a toast. In a real app, you would navigate to a SignupActivity.
            Toast.makeText(this, "Sign Up screen is not implemented yet.", Toast.LENGTH_SHORT).show()

            // Example of how you would start a SignupActivity:
            // val intent = Intent(this, SignupActivity::class.java)
            // startActivity(intent)
        }
    }

    /**
     * Handles the login logic when the login button is clicked.
     */
    private fun handleLogin() {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        // --- Basic Input Validation ---
        if (email.isEmpty()) {
            emailEditText.error = "Email is required"
            emailEditText.requestFocus()
            return
        }

        if (password.isEmpty()) {
            passwordEditText.error = "Password is required"
            passwordEditText.requestFocus()
            return
        }

        // --- Dummy Login Logic ---
        // In a real application, you would replace this with a call to your
        // authentication service (e.g., Firebase Auth, your own backend).
        if (email == "user@example.com" && password == "password123") {
            // Login successful
            Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()

            // Navigate to the HomeActivity
            navigateToHome()
        } else {
            // Login failed
            Toast.makeText(this, "Invalid email or password.", Toast.LENGTH_LONG).show()
        }
    }

    /**
     * Navigates to the HomeActivity and clears the activity stack.
     */
    private fun navigateToHome() {
        val intent = Intent(this, HomeActivity::class.java).apply {
            // Flags to clear the back stack, so the user can't go back to the login screen
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
    }
}

class Loginactivity {
}