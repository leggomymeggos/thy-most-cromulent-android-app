package com.mgaebz.cromulence

import android.arch.lifecycle.MutableLiveData

class InsultRepository {

    private val insults: MutableList<Insult> = mutableListOf(
            Insult(1),
            Insult(2),
            Insult(3)
    )
    private val liveListData: MutableLiveData<List<Insult>> = MutableLiveData()

    private val insultLiveData: MutableLiveData<Insult> = MutableLiveData()

    fun getMessages(): MutableLiveData<List<Insult>> {
        liveListData.value = insults
        return liveListData
    }

    fun getMessage(id: Int): MutableLiveData<Insult> {
        insultLiveData.value = insults.first { it.id == id }
        return insultLiveData
    }

    fun save(insult: Insult) {
        insults.add(insult)
        liveListData.postValue(insults)
    }
}