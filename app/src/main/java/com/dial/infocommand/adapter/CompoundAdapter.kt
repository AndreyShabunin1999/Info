package com.dial.infocommand.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.dial.infocommand.R
import com.dial.infocommand.model.InfoModel

class CompoundAdapter(private var context: Context, private var pos: Int, private var pos2: Int, private var countries: ArrayList<InfoModel>): RecyclerView.Adapter<CompoundAdapter.CompoundViewHolder>() {

    inner class CompoundViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgFootballPlayer: ImageView = itemView.findViewById(R.id.img_football_player)
        val textNumberAndName: TextView = itemView.findViewById(R.id.tv_number_and_name_football_player)
        val tvOldAndStaff: TextView = itemView.findViewById(R.id.tv_staff_football_player)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompoundViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_compound , parent , false)
        return CompoundViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countries[pos].commands[pos2].compound.size
    }

    override fun onBindViewHolder(holder: CompoundViewHolder, position: Int) {
        Glide
            .with(context)
            .load(countries[pos].commands[pos2].compound[position].img)
            .placeholder(R.color.white)
            .into(holder.imgFootballPlayer)

        holder.textNumberAndName.text = String.format(context.resources.getString(R.string.text_number_and_name), countries[pos].commands[pos2].compound[position].number,
            countries[pos].commands[pos2].compound[position].name)

        holder.tvOldAndStaff.text = String.format(context.resources.getString(R.string.text_staff_and_old), countries[pos].commands[pos2].compound[position].position,
            countries[pos].commands[pos2].compound[position].old)
    }
}