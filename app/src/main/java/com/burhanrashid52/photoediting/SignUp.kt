package com.burhanrashid52.photoediting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val actionBar = supportActionBar


        actionBar?.title = "Sign Up"

        firebaseAuth = FirebaseAuth.getInstance()

        val emailEditText = findViewById<EditText>(R.id.email3)
        val passwordEditText = findViewById<EditText>(R.id.password)
        val signUpButton = findViewById<Button>(R.id.signUp)

        signUpButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Call the function to create a user with email and password
            createUserWithEmailAndPassword(email, password)
        }
    }

    private fun createUserWithEmailAndPassword(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    Toast.makeText(this, "Sign-up successful!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, Account::class.java)
                    startActivity(intent)
                } else {
                    val errorMessage = task.exception?.message

                    // Check if the error message indicates a weak password
                    if (errorMessage == "The given password is invalid. [ Password should be at least 6 characters ]") {
                        // Show a specific message for weak password
                        Toast.makeText(this, "Please use a stronger password (at least 6 characters).", Toast.LENGTH_LONG).show()
                    } else {

                        Toast.makeText(this, "Sign-up failed: $errorMessage", Toast.LENGTH_LONG).show()
                    }
                }
            }
    }

}
