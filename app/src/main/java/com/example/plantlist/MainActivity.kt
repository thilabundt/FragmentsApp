package com.example.plantlist

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), ItemFragment.OnEventSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (findViewById<View>(R.id.fragment_container) != null) {
            if (savedInstanceState == null) {
                val firstFragment = ItemFragment.newInstance(1)
                supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, firstFragment)
                    .commit()

                if (findViewById<View>(R.id.detail_container) != null) {
                    val detailFragment = DetailFragment()
                    supportFragmentManager.beginTransaction()
                        .add(R.id.detail_container, detailFragment)
                        .commit()
                }
            }
        }
    }

    override fun onEventSelected(plant: Plant) {
        val detailFragment = supportFragmentManager.findFragmentById(R.id.detail_container) as? DetailFragment

        if (detailFragment != null) {
            detailFragment.updateEventDetails(plant)
        } else {
            val newFragment = DetailFragment().apply {
                arguments = Bundle().apply {
                    putString("plant_name", plant.plantName)
                    putString("plant_latin_name", plant.plantLatinName)
                    putString("event_description", plant.plantDescription)
                }
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, newFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}