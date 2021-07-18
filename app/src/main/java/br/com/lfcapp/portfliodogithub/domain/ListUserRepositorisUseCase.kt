package br.com.lfcapp.portfliodogithub.domain

import br.com.lfcapp.portfliodogithub.core.UseCase
import br.com.lfcapp.portfliodogithub.data.model.Repo
import br.com.lfcapp.portfliodogithub.data.repositories.RepoRepository
import kotlinx.coroutines.flow.Flow

class ListUserRepositorisUseCase(private val repository: RepoRepository) :
    UseCase<String, List<Repo>>() {

    override suspend fun execute(param: String): Flow<List<Repo>> {
        return repository.listRepositories(param)
    }

}