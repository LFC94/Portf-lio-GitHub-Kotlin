package br.com.lfcapp.portfliodogithub.data.repositories

import br.com.lfcapp.portfliodogithub.data.services.GitHubService
import kotlinx.coroutines.flow.flow

class RepoRepositoryImpl(private val service: GitHubService) : RepoRepository {

    override suspend fun listRepositories(user: String) = flow {
        val repoList = service.listRepos(user)
        emit(repoList)
    }
}