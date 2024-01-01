package com.burhanrashid52.photoediting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val actionBar = supportActionBar


        actionBar?.title = "Log In"

        firebaseAuth = FirebaseAuth.getInstance()

        val loginButton = findViewById<Button>(R.id.login)
        val emailEditText = findViewById<EditText>(R.id.email)
        val passwordEditText = findViewById<EditText>(R.id.password)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Perform login using Firebase Authentication
            loginUser(email, password)
        }

        val signUpButton = findViewById<Button>(R.id.button3)
        signUpButton.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            startActivity(Intent(this, Account::class.java))
            finish()
        }

        val forgotPasswordButton = findViewById<Button>(R.id.forgotPasswordButton)
        forgotPasswordButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            if (email.isNotEmpty()) {
                resetPassword(email)
            } else {
                // Show a message to enter an email address
                Toast.makeText(this, "Enter your registered email address", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loginUser(email: String, password: String) {

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, Account::class.java)
                    startActivity(intent)
                } else {

                }
            }
    }
    private fun resetPassword(email: String) {
        val auth = FirebaseAuth.getInstance()
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Reset email sent to $email", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Failed to send reset email", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
