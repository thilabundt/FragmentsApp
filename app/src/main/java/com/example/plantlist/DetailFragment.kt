package com.example.plantlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class DetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val plantName = it.getString("plant_name")
            val plantLatinName = it.getString("plant_latin_name")
            val plantDescription = it.getString("plant_description")
            view.findViewById<TextView>(R.id.plant_name_detail)?.text = plantName
            view.findViewById<TextView>(R.id.plant_latin_name_detail)?.text = plantLatinName
            view.findViewById<TextView>(R.id.plant_description_detail)?.text = plantDescription
        }
    }

    fun updateEventDetails(plant: Plant) {
        view?.let { view ->
            val plantNameTextView = view.findViewById<TextView>(R.id.plant_name_detail)
            val plantLatinNameTextView = view.findViewById<TextView>(R.id.plant_latin_name_detail)
            val plantDescriptionTextView = view.findViewById<TextView>(R.id.plant_description_detail)

            plantNameTextView.text = plant.plantName
            plantLatinNameTextView.text = plant.plantLatinName
            plantDescriptionTextView.text = plant.plantDescription

            val descriptionTextView = view?.findViewById<TextView>(R.id.plant_description_detail)
            plantDescriptionTextView?.visibility = View.VISIBLE

            val shareButton = view?.findViewById<Button>(R.id.button_detail)
            shareButton?.visibility = View.VISIBLE
        }
    }


}
