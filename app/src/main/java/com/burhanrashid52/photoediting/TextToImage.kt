package com.burhanrashid52.photoediting

import android.app.DownloadManager.Request
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.RetryPolicy
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.JsonRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.lang.reflect.Method

class TextToImage : AppCompatActivity() {
    var url = "https://api.openai.com/v1/images/generations"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_text_to_image)

        val actionBar = supportActionBar

        // Set a custom title for the action bar
        actionBar?.title = "Text to Image"
        val generate = findViewById<Button>(R.id.Generate)
        generate.setOnClickListener {

        }
    }


//
//    private fun getResonse (){
//        val queue: RequestQueue = Volley.newRequestQueue(applicationContext)
//        val jsonObject: JSONObject? = JSONObject()
//        jsonObject?.put("prompt", "Dog")
//        jsonObject?.put("n",1)
//        jsonObject?.put("size","256x256")
//
//
//        val postRequest: JsonObjectRequest = object  : JsonObjectRequest(Method.POST,url,jsonObject,Response.Listener { response ->
//
//
//
//        },Response.ErrorListener { error -> Toast.makeText(applicationContext,"filed",Toast.LENGTH_LONG).show() }) {
//            override fun getHeaders(): MutableMap<String, String> {
//                val params: MutableMap<String,String> =HashMap();
//                params["Content-Type"] = "application/json"
//                params["Authorization"] = "Bearer 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"
//                return params
//            }
//        }
//        postRequest.setRetryPolicy(object : RetryPolicy {
//            override fun getCurrentTimeout(): Int {
//                return 50000
//            }
//
//            override fun getCurrentRetryCount(): Int {
//                return 50000
//            }
//
//            override fun retry(error: VolleyError?) {
//
//            }
//
//        })
//        queue.add(postRequest)
//
//    }
}