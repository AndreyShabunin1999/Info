package com.dial.infocommand.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dial.infocommand.model.CommandsModel
import com.dial.infocommand.model.InfoModel
import com.dial.infocommand.repository.CommandsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CommandsViewModel(private val commandsRepository: CommandsRepository): ViewModel() {
    init {
        viewModelScope.launch (Dispatchers.IO){
            commandsRepository.getCommands()
        }
    }

    val commands: LiveData<ArrayList<InfoModel>>
        get() = commandsRepository.commands
}