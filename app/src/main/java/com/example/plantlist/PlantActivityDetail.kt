package com.example.plantlist

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PlantActivityDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_detail)

        val plantName = intent.getStringExtra("plantName") ?: "No name"
        val plantLatinName = intent.getStringExtra("plantLatinName") ?: "No latin name"
        val plantDescription = intent.getStringExtra("plantDescription") ?: "No description"

        findViewById<TextView>(R.id.plant_name_detail).text = plantName
        findViewById<TextView>(R.id.plant_latin_name_detail).text = plantLatinName
        findViewById<TextView>(R.id.plant_description_detail).text = plantDescription

        val buttonQuery = findViewById<Button>(R.id.button_detail)
        buttonQuery.setOnClickListener {
            val queryUrl = "https://www.google.com/search?q=$plantName"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(queryUrl))
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                Log.d("PlantActivityDetail", "No activity can handle the intent")
            }
        }
    }
    }
