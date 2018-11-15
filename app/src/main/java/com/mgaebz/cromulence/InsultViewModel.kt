package com.mgaebz.cromulence

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel

class InsultViewModel(private val insultRepository: InsultRepository) : ViewModel() {
    private val messageId: MutableLiveData<Int> = MutableLiveData()

    val messages: LiveData<List<Insult>> = insultRepository.getMessages()

    val insult: LiveData<Insult> = Transformations.switchMap(messageId) {
        insultRepository.getMessage(it)
    }

    fun addMessage(insult: Insult) {
        insultRepository.save(insult)
    }
}