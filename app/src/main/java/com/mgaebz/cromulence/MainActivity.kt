package com.mgaebz.cromulence

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var viewAdapter: InsultsAdapter
    lateinit var viewManager: LinearLayoutManager

    val insultViewModel: InsultViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewAdapter = InsultsAdapter(listOf(), this)
        viewManager = LinearLayoutManager(this)
        recyclerView = findViewById(R.id.insults_recycler_view)

        recyclerView.apply {
            viewManager.stackFromEnd = true
            layoutManager = viewManager
            adapter = viewAdapter
        }

        insultViewModel.messages.observe(this, Observer {
            viewAdapter.updateInsults(it!!)
            recyclerView.smoothScrollToPosition(viewAdapter.itemCount)
        })

        findViewById<Button>(R.id.generator_button).setOnClickListener {
            insultViewModel.addMessage(Insult(viewAdapter.itemCount + 1))
        }
    }
}
