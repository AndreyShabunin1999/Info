package com.dial.infocommand.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dial.infocommand.api.ApiInterface
import com.dial.infocommand.model.InfoModel

class CommandsRepository(private val apiInterface: ApiInterface) {
    private val infoCommandsLiveData = MutableLiveData<ArrayList<InfoModel>>()

    val commands: LiveData<ArrayList<InfoModel>>
        get() = infoCommandsLiveData

    suspend fun getCommands() {
        val result = apiInterface.getCommands()
        if(result.body() != null) {
            infoCommandsLiveData.postValue(result.body())
        }
    }
}