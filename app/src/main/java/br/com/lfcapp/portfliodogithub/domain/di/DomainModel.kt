package br.com.lfcapp.portfliodogithub.domain.di

import br.com.lfcapp.portfliodogithub.domain.ListUserRepositorisUseCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModel {
    fun load() {
        loadKoinModules(useCaseModel())
    }

    private fun useCaseModel(): Module {
        return module {
            factory {
                ListUserRepositorisUseCase(get())
            }
        }
    }
}