package com.dial.infocommand.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.dial.infocommand.R
import com.dial.infocommand.model.InfoModel

class CommandsAdapter(private var context: Context, private var pos: Int, private var countries: ArrayList<InfoModel>,
                        private var onItemClicked: ((position: Int) -> Unit)): RecyclerView.Adapter<CommandsAdapter.TableViewHolder>() {

    inner class TableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgClub: ImageView = itemView.findViewById(R.id.img_flag_club)
        val textTitle: TextView = itemView.findViewById(R.id.tv_name_command)
        val textDescription: TextView = itemView.findViewById(R.id.tv_descriptions_command)
        val btnCompound: LinearLayout = itemView.findViewById(R.id.btn_compound)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comand , parent , false)
        return TableViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countries[pos].commands.size
    }

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        holder.textTitle.text = countries[pos].commands[position].name
        holder.textDescription.text = countries[pos].commands[position].description

        Glide
            .with(context)
            .load(countries[pos].commands[position].image)
            .placeholder(R.color.white)
            .into(holder.imgClub)

        holder.btnCompound.setOnClickListener {
            onItemClicked(position)
        }
    }
}