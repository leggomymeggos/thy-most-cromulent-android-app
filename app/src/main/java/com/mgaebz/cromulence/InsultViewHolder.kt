package com.mgaebz.cromulence

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class InsultViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    fun setText(insult: Insult) {
        view.findViewById<TextView>(R.id.insult_text).text = insult.text
    }
}
