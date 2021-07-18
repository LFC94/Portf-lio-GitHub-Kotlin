package br.com.lfcapp.portfliodogithub.presentation.di

import br.com.lfcapp.portfliodogithub.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object PresentationModule {
    fun load() {
        loadKoinModules(viewModelModel())
    }

    private fun viewModelModel(): Module {
        return module {
            viewModel {
                MainViewModel(get())
            }
        }
    }
}