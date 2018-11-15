package com.mgaebz.cromulence

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel

class MessageViewModel(val messageRepository: MessageRepository) : ViewModel() {
    private val messageId: MutableLiveData<Int> = MutableLiveData()

    val messages: LiveData<List<Message>> = messageRepository.getMessages()

    val message: LiveData<Message> = Transformations.switchMap(messageId) {
        messageRepository.getMessage(it)
    }

    fun loadMessage(id: Int) {
        messageId.value = id
    }

    fun addMessage(message: Message) {
        messageRepository.save(message)
    }
}