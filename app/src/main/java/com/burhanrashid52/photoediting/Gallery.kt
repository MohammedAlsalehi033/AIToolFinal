package com.burhanrashid52.photoediting


import android.content.Intent
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide

class Gallery : AppCompatActivity() {
    private val storage = Firebase.storage
    private val storageRef = storage.reference.child("gs://myfirstapp-c49e9.appspot.com/images")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallary)

        val fitchdata = findViewById<Button>(R.id.fitchData)
        fitchdata.setOnClickListener {
            fetchImageUrlsFromFolder()
        }
    }


    private fun fetchImageUrlsFromFolder() {

        storageRef.listAll()
            .addOnSuccessListener { listResult ->

                for (item in listResult.items) {
                    Log.d("adffffffffffffffffff", "fffffffffffffffffffffffffffffffffffffffffff")
                    item.downloadUrl.addOnSuccessListener { uri ->
                        val imageUrl = uri.toString()
                        // Process each imageUrl (e.g., display, store, or use as needed)
                        // Here, you might add the URLs to a list, display them, etc.
                        // Example: Log the URLs for demonstration

                        println("Image URL: $imageUrl")
                    }.addOnFailureListener { exception ->
                        // Handle any errors while fetching download URLs
                    }
                }
            }
            .addOnFailureListener { exception ->
                // Handle any errors while listing items in the folder
            }
    }
    }
