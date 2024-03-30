package com.example.plantlist

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ItemFragment : Fragment() {

    private var columnCount = 1
    private var listener: OnEventSelectedListener? = null

    interface OnEventSelectedListener {
        fun onEventSelected(plant: Plant)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnEventSelectedListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnEventSelectedListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        val plants = listOf(
            Plant("Rose", "Rosa", "A thorny flower known for its beauty and fragrance."),
            Plant("Tulip", "Tulipa", "A bulbous spring-flowering plant with bold, colorful flowers."),
            Plant("Sunflower", "Helianthus", "A large annual plant with a tall stem and a big daisy-like flower head."),
            Plant("Lavender", "Lavandula", "A small aromatic evergreen shrub with purple flowers, used in perfumery."),
            Plant("Orchid", "Orchidaceae", "A diverse and widespread family of flowering plants with blooms that are often colorful and fragrant."),
            Plant("Daisy", "Bellis perennis", "A common European species of daisy, of the Asteraceae family, often considered the archetypal species of that name."),
            Plant("Lily", "Lilium", "A genus of herbaceous flowering plants growing from bulbs, all with large prominent flowers."),
            Plant("Cactus", "Cactaceae", "A member of the plant family Cactaceae, a family comprising about 127 genera with some 1750 known species of the order Caryophyllales."),
            Plant("Bamboo", "Bambusoideae", "A subfamily of grasses, more specifically the woody grasses of the tribe Bambuseae."),
            Plant("Aloe Vera", "Aloe vera", "A succulent plant species of the genus Aloe. An evergreen perennial, it originates from the Arabian Peninsula but grows wild in tropical climates around the world."),
            Plant("Mint", "Mentha", "A genus of plants in the family Lamiaceae. Mint is aromatic, almost exclusively perennial, and rarely annual or biannual."),
            Plant("Fern", "Polypodiopsida", "A group of about 10,560 known species of plants that differ from the mosses by being vascular, i.e., having specialized tissues that conduct water and nutrients, and having life cycles in which the sporophyte is the dominant phase."),
            Plant("Ivy", "Hedera", "A genus of 12 to 15 species of evergreen climbing or ground-creeping woody plants in the family Araliaceae."),
            Plant("Oak", "Quercus", "A tree or shrub in the genus Quercus of the beech family, Fagaceae. There are approximately 600 extant species of oaks."),
            Plant("Pine", "Pinus", "A genus of trees in the family Pinaceae that are native to most of the Northern Hemisphere."),
            Plant("Maple", "Acer", "A genus of trees or shrubs. They are variously classified in a family of their own, the Aceraceae, or together with the Hippocastanaceae included in the family Sapindaceae."),
            Plant("Willow", "Salix", "A genus of deciduous trees and shrubs, found primarily on moist soils in cold and temperate regions of the Northern Hemisphere."),
            Plant("Peony", "Paeonia", "A genus of herbaceous perennials and shrubs in the family Paeoniaceae. They are native to Asia, Europe and Western North America."),
            Plant("Hydrangea", "Hydrangea", "A genus of 70–75 species of flowering plants native to Asia and the Americas. By far the greatest species diversity is in eastern Asia, notably Korea, China, and Japan."),
            Plant("Venus Flytrap", "Dionaea muscipula", "A carnivorous plant native to subtropical wetlands on the East Coast of the United States in North Carolina and South Carolina.")
        )

        val recyclerView = view.findViewById<RecyclerView>(R.id.itemsRecyclerView)
        recyclerView.layoutManager = when {
            columnCount <= 1 -> LinearLayoutManager(context)
            else -> GridLayoutManager(context, columnCount)
        }
        recyclerView.adapter = MyItemRecyclerViewAdapter(plants, listener!!::onEventSelected)

        return view
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            ItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}

