package com.mgaebz.cromulence

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

class InsultsAdapter(var insults: List<Insult>, val context: Context) : RecyclerView.Adapter<InsultViewHolder>() {

    override fun getItemCount(): Int = insults.size

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): InsultViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.insult_layout, parent, false)
        return InsultViewHolder(view)
    }

    override fun onBindViewHolder(holder: InsultViewHolder, position: Int) {
        holder.setText(insults[position])

        holder.itemView.setOnLongClickListener {insultView ->

            val mainActivity = context as MainActivity
            val selectedInsultView = mainActivity.findViewById<LinearLayout>(R.id.selected_insult)
            val selectedInsultText = selectedInsultView.findViewById<TextView>(R.id.selected_insult_text)
            selectedInsultText.text = insults[position].text
            selectedInsultText.x = insultView.x
            selectedInsultText.y = insultView.y
            selectedInsultText.background = mainActivity.getDrawable(R.drawable.selected_insult_background)

            val bottomSheet = InsultActionsBottomDialogFragment()
            bottomSheet.show(mainActivity.supportFragmentManager, "insult_actions_dialog_fragment")

            return@setOnLongClickListener true
        }
    }

    fun updateInsults(newInsults: List<Insult>) {
        insults = newInsults
        notifyDataSetChanged()
    }
}