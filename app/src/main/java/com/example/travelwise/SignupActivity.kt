package com.example.travelwise

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.travelwise.databinding.ActivitySignupBinding
import com.example.travelwise.ui.home.HomeActivity

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hide action bar
        supportActionBar?.hide()

        // Back button click listener
        binding.btnBack.setOnClickListener {
            finish()
        }

        // Sign up button click listener
        binding.btnSignUp.setOnClickListener {
            val fullName = binding.etFullName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val phone = binding.etPhone.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val confirmPassword = binding.etConfirmPassword.text.toString().trim()
            val termsAccepted = binding.cbTerms.isChecked

            if (validateInput(fullName, email, phone, password, confirmPassword, termsAccepted)) {
                // For now, just navigate to HomeActivity
                // In production, you would register the user with a backend
                Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
        }

        // Terms and Conditions click listener
        binding.tvTerms.setOnClickListener {
            Toast.makeText(this, "Terms & Conditions clicked", Toast.LENGTH_SHORT).show()
            // TODO: Show terms and conditions dialog or navigate to terms page
        }

        // Login link click listener
        binding.tvLogin.setOnClickListener {
            // Navigate back to login screen
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Google sign up click listener
        binding.btnGoogleSignUp.setOnClickListener {
            Toast.makeText(this, "Google Sign Up clicked", Toast.LENGTH_SHORT).show()
            // TODO: Implement Google sign up
        }

        // Facebook sign up click listener
        binding.btnFacebookSignUp.setOnClickListener {
            Toast.makeText(this, "Facebook Sign Up clicked", Toast.LENGTH_SHORT).show()
            // TODO: Implement Facebook sign up
        }
    }

    private fun validateInput(
        fullName: String,
        email: String,
        phone: String,
        password: String,
        confirmPassword: String,
        termsAccepted: Boolean
    ): Boolean {
        // Validate full name
        if (fullName.isEmpty()) {
            binding.etFullName.error = "Full name is required"
            binding.etFullName.requestFocus()
            return false
        }

        if (fullName.length < 3) {
            binding.etFullName.error = "Name must be at least 3 characters"
            binding.etFullName.requestFocus()
            return false
        }

        // Validate email
        if (email.isEmpty()) {
            binding.etEmail.error = "Email is required"
            binding.etEmail.requestFocus()
            return false
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etEmail.error = "Please enter a valid email"
            binding.etEmail.requestFocus()
            return false
        }

        // Validate phone
        if (phone.isEmpty()) {
            binding.etPhone.error = "Phone number is required"
            binding.etPhone.requestFocus()
            return false
        }

        if (phone.length < 10) {
            binding.etPhone.error = "Please enter a valid phone number"
            binding.etPhone.requestFocus()
            return false
        }

        // Validate password
        if (password.isEmpty()) {
            binding.etPassword.error = "Password is required"
            binding.etPassword.requestFocus()
            return false
        }

        if (password.length < 6) {
            binding.etPassword.error = "Password must be at least 6 characters"
            binding.etPassword.requestFocus()
            return false
        }

        // Validate confirm password
        if (confirmPassword.isEmpty()) {
            binding.etConfirmPassword.error = "Please confirm your password"
            binding.etConfirmPassword.requestFocus()
            return false
        }

        if (password != confirmPassword) {
            binding.etConfirmPassword.error = "Passwords do not match"
            binding.etConfirmPassword.requestFocus()
            return false
        }

        // Validate terms acceptance
        if (!termsAccepted) {
            Toast.makeText(this, "Please accept the Terms & Conditions", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}

