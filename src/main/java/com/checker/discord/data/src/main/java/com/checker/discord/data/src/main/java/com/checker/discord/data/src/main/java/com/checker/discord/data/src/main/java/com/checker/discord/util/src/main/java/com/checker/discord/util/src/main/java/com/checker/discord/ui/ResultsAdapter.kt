package com.checker.discord.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.checker.discord.R
import com.checker.discord.data.UsernameResult

class ResultsAdapter(private var results: List<UsernameResult>) :
    RecyclerView.Adapter<ResultsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvUsername: TextView = view.findViewById(R.id.tvUsername)
        val tvStatus: TextView = view.findViewById(R.id.tvStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_result, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = results[position]
        holder.tvUsername.text = item.username
        holder.tvStatus.text = if (item.isAvailable) "متاح ✅" else "مأخوذ ❌"
    }

    override fun getItemCount() = results.size

    fun updateData(newResults: List<UsernameResult>) {
        results = newResults
        notifyDataSetChanged()
    }
    }
    
