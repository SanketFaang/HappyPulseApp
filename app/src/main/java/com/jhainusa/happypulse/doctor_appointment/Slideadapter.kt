package com.jhainusa.happypulse.doctor_appointment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jhainusa.happypulse.R
import com.squareup.picasso.Picasso

class Slideadapter(var doctorArray: List<doctorinfo>) : RecyclerView.Adapter<Slideadapter.SlideViewHolder>() {
    private lateinit var mylistener : onItemClickListener

    interface onItemClickListener {
        fun onItemCLick(position: Int)
    }
    fun setItemCLickListener(listener: onItemClickListener){
        mylistener=listener
    }
    inner class SlideViewHolder(itemView: View , listener: onItemClickListener):RecyclerView.ViewHolder(itemView){
        val imageView :ImageView = itemView.findViewById(R.id.ImageView)
        val docname:TextView=itemView.findViewById(R.id.docname)
        val docexp:TextView=itemView.findViewById(R.id.docexp)
        val hosname:TextView=itemView.findViewById(R.id.hosname)
        val hosadd:TextView=itemView.findViewById(R.id.docadd)

        fun bind(doctor : doctorinfo){
            docname.text=doctor.docname
            docexp.text=doctor.docexp
            hosadd.text=doctor.docadd
            hosname.text=doctor.hosname
            Picasso.get().load(doctor.docimg).into(imageView)

        }
        init{
            itemView.setOnClickListener{
                listener.onItemCLick(adapterPosition)
            }
    }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlideViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_doctor_appointment,parent,false)
        return SlideViewHolder(view,mylistener)
    }

    override fun getItemCount(): Int {
        return doctorArray.size
    }

    override fun onBindViewHolder(holder: SlideViewHolder, position: Int) {
        val currentItem = doctorArray[position]
        holder.docname.text=currentItem.docname
        holder.docexp.text=currentItem.docexp
        holder.hosadd.text=currentItem.docadd
        holder.hosname.text=currentItem.hosname
        Picasso.get().load(currentItem.docimg).into(holder.imageView)
    }


}