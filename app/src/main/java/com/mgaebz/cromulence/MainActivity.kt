package com.mgaebz.cromulence

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var viewAdapter: MessagesAdapter
    lateinit var viewManager: LinearLayoutManager

    val insultViewModel: InsultViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewAdapter = MessagesAdapter(listOf())
        viewManager = LinearLayoutManager(this)
        recyclerView = findViewById(R.id.insults_recycler_view)

        recyclerView.apply {
            viewManager.stackFromEnd = true
            layoutManager = viewManager
            adapter = viewAdapter
        }

        insultViewModel.messages.observe(this, Observer {
            viewAdapter.updateMessages(it!!)
            recyclerView.smoothScrollToPosition(viewAdapter.itemCount)
        })

        findViewById<Button>(R.id.generator_button).setOnClickListener {
            insultViewModel.addMessage(Insult(viewAdapter.itemCount + 1))
        }
        // set background to "stale" like 3 seconds later
        // maybe viewholder can observe individual insult?????
    }
}

class MessagesAdapter(var insults: List<Insult>) : RecyclerView.Adapter<MessageViewHolder>() {

    override fun getItemCount(): Int = insults.size

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.insult_layout, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.setMessageText(insults[position])
    }

    fun updateMessages(newInsults: List<Insult>) {
        insults = newInsults
        notifyDataSetChanged()
    }
}

class MessageViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    fun setMessageText(insult: Insult) {
        view.findViewById<TextView>(R.id.insult_text).text = insult.text
    }
}
