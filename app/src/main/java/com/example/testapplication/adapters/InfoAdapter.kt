package com.example.testapplication.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.R
import com.example.testapplication.models.requests.GenericKeyValueItem


class InfoAdapter(
    private var items: List<GenericKeyValueItem>
    ): RecyclerView.Adapter<InfoAdapter.ViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_info_adapter, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setContent(items[position])

        holder.itemView.setOnClickListener() {
            val item = items[position]
            if (item.Value.trim().isNotEmpty()) {
                val uri: Uri = Uri.parse(item.Value.trim())
                val goToInfo = Intent(Intent.ACTION_VIEW, uri)
                goToInfo.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                startActivity(context, goToInfo, null)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private var bckgInfo: RelativeLayout
        private var textInfo: TextView
        private lateinit var linkInfo: String

        init {
            bckgInfo = itemView.findViewById(R.id.background_Info)
            textInfo = itemView.findViewById(R.id.text_Info)
        }

        fun setContent(item: GenericKeyValueItem) {
            textInfo.text = item.Key
            linkInfo = item.Key
        }

    }

}