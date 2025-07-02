package com.example.diagnostic_android_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class UdsListFragment : Fragment(R.layout.fragment_uds_list) {
    private val items = listOf(
        UdsItem(1, "Speed Sensor", "Monitors speed", R.drawable.carspeed1),
        UdsItem(2, "Oil Temp Sensor", "Tracks oil temp", R.drawable.thermometer1),
        UdsItem(3, "MAF Sensor", "Measures airflow", R.drawable.airflow1)
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.uds_list)
        recycler?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = UdsAdapter(items) { itemId ->
                findNavController().navigate(
                    R.id.action_uds_list_to_uds_detail,
                    Bundle().apply { putInt("itemId", itemId) }
                )
            }
        }
    }
}

class UdsAdapter(
    private val items: List<UdsItem>,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<UdsAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon: ImageView = view.findViewById(R.id.icon)!!
        val name: TextView = view.findViewById(R.id.name)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_uds, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.icon.setImageResource(item.iconRes)
        holder.name.text = item.name
        holder.itemView.setOnClickListener { onItemClick(item.id) }
    }

    override fun getItemCount() = items.size
}
