package com.burhanrashid52.photoediting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button
import android.widget.TextView
import android.widget.Toast

import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.text.SimpleDateFormat
import java.util.*

class Account : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var currentUser: FirebaseUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        firebaseAuth = FirebaseAuth.getInstance()
        currentUser = firebaseAuth.currentUser!!





        // Display last login time
        val lastLoginTextView = findViewById<TextView>(R.id.lastLoginTextView)
        val emailTextView = findViewById<TextView>(R.id.emailTextView)
        emailTextView.text = "Email: ${currentUser.email}"
        val lastLoginMillis = currentUser.metadata?.lastSignInTimestamp
        if (lastLoginMillis != null) {
            val lastLoginDate = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())
                .format(Date(lastLoginMillis))
            lastLoginTextView.text = "Last Login: $lastLoginDate"
        }

        // Handle "Change Password" button click
        val changePasswordButton = findViewById<Button>(R.id.changePasswordButton)
        changePasswordButton.setOnClickListener {
            // Call function to initiate password change flow
            initiatePasswordChange()
        }

        val signOutButton = findViewById<Button>(R.id.signOutButton)
        signOutButton.setOnClickListener {
            signOutUser()
        }
    }

    private fun initiatePasswordChange() {
        firebaseAuth.sendPasswordResetEmail(currentUser.email!!)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Password reset email sent!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Failed to send password reset email", Toast.LENGTH_SHORT).show()
                }
            }
    }
    private fun signOutUser() {
        firebaseAuth.signOut()
        // After signing out, redirect the user to the login screen
        val intent = Intent(this, Login::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish() // Prevents going back to the AccountActivity on pressing back
    }
}