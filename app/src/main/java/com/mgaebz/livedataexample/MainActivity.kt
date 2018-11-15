package com.mgaebz.livedataexample

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

    val messageViewModel: MessageViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewAdapter = MessagesAdapter(listOf())
        viewManager = LinearLayoutManager(this)
        recyclerView = findViewById(R.id.messages_recycler_view)

        recyclerView.apply {
            viewManager.stackFromEnd = true
            layoutManager = viewManager
            adapter = viewAdapter
        }

        messageViewModel.messages.observe(this, Observer {
            viewAdapter.updateMessages(it!!)
            recyclerView.smoothScrollToPosition(viewAdapter.itemCount)
        })

        findViewById<Button>(R.id.generator_button).setOnClickListener {
            messageViewModel.addMessage(Message(viewAdapter.itemCount + 1))
        }
        // set background to "stale" like 3 seconds later
        // maybe viewholder can observe individual message?????
    }
}

class MessagesAdapter(var messages: List<Message>) : RecyclerView.Adapter<MessageViewHolder>() {

    override fun getItemCount(): Int = messages.size

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.message_layout, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.setMessageText(messages[position])
    }

    fun updateMessages(newMessages: List<Message>) {
        messages = newMessages
        notifyDataSetChanged()
    }
}

class MessageViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    fun setMessageText(message: Message) {
        view.findViewById<TextView>(R.id.message_text).text = message.text
    }
}
