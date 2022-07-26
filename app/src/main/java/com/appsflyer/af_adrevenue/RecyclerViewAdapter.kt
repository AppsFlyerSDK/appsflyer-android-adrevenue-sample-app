package com.appsflyer.af_adrevenue

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.appsflyer.af_adrevenue.data.AdTypeData


class RecyclerViewAdapter(private var dataSet: List<AdTypeData>, private val onItemClick: (position: Int, view: View?) -> Unit) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val imageView: ImageView
        val root:CardView

        init {
            imageView = view.findViewById(R.id.image_view)
            textView = view.findViewById(R.id.text_view)
            root = view as CardView
        }
    }
    fun updateList(adTypes: List<AdTypeData>){
        this.dataSet = adTypes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.ad_type, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = dataSet[position].title
        viewHolder.imageView.setImageResource(dataSet[position].imgSrc)
        viewHolder.root.setOnClickListener{
            onItemClick(position,it)
        }
    }

    override fun getItemCount() = dataSet.size


}

