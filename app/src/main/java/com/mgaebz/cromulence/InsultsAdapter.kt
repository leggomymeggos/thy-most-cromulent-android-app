package com.mgaebz.cromulence

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class InsultsAdapter(var insults: List<Insult>, val context: Context) : RecyclerView.Adapter<InsultViewHolder>() {

    override fun getItemCount(): Int = insults.size

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): InsultViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.insult_layout, parent, false)
        return InsultViewHolder(view)
    }

    override fun onBindViewHolder(holder: InsultViewHolder, position: Int) {
        holder.setText(insults[position])

        holder.itemView.setOnLongClickListener {insultView ->

            val bottomSheet = InsultActionsBottomDialogFragment()
            bottomSheet.show((context as MainActivity).supportFragmentManager, "insult_actions_dialog_fragment")


            return@setOnLongClickListener true
        }
    }

    fun updateInsults(newInsults: List<Insult>) {
        insults = newInsults
        notifyDataSetChanged()
    }
}