package com.dial.infocommand.adapter

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.dial.infocommand.R
import com.dial.infocommand.model.InfoModel


class CountryBtnAdapter(private var context: Context, private var countries: ArrayList<InfoModel>,
                        private var onItemClicked: ((position: Int) -> Unit)): RecyclerView.Adapter<CountryBtnAdapter.TableViewHolder>() {

    inner class TableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btnTable: Button = itemView.findViewById(R.id.item_btn_country)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_btn_country , parent , false)
        return TableViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        holder.btnTable.text = countries[position].country

        holder.btnTable.setOnClickListener {
            val scale = AnimationUtils.loadAnimation(context, R.anim.scale)
            holder.btnTable.startAnimation(scale)

            Handler(Looper.getMainLooper()).postDelayed({
                onItemClicked(position)
            }, 500)
        }

    }
}