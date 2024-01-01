package com.burhanrashid52.photoediting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Replace with your layout file name

        val imageEditorButton = findViewById<Button>(R.id.imageEditor)
        val account= findViewById<Button>(R.id.Account)
        val imageGenerator = findViewById<Button>(R.id.imageGenerator)
        val gallery = findViewById<Button>(R.id.gallery)

        gallery.setOnClickListener {
            val intent = Intent(this, Gallery::class.java)
            startActivity(intent)
        }

        imageEditorButton.setOnClickListener {
            navigateToImageEditor()
        }
        imageGenerator.setOnClickListener {
            val intent = Intent(this, TextToImage::class.java)
            startActivity(intent)
        }
        account.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }

    private fun navigateToImageEditor() {
        val intent = Intent(this, EditImageActivity::class.java)
        startActivity(intent)
    }
}