package com.mgaebz.livedataexample

import android.arch.lifecycle.MutableLiveData

class MessageRepository {

    val messages: MutableList<Message> = mutableListOf(
            Message(1),
            Message(2),
            Message(3)
    )
    val liveListData: MutableLiveData<List<Message>> = MutableLiveData()

    val messageLiveData: MutableLiveData<Message> = MutableLiveData()

    fun getMessages(): MutableLiveData<List<Message>> {
        liveListData.value = messages
        return liveListData
    }

    fun getMessage(id: Int): MutableLiveData<Message> {
        messageLiveData.value = messages.first { it.id == id }
        return messageLiveData
    }

    fun save(message: Message) {
        messages.add(message)
        liveListData.postValue(messages)
    }

}