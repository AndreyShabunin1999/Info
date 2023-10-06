package com.dial.infocommand.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dial.infocommand.repository.CommandsRepository

class CommandsViewModelFactory(private val commandsRepository: CommandsRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CommandsViewModel(commandsRepository) as T
    }
}