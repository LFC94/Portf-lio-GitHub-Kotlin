package br.com.lfcapp.portfliodogithub.data.repositories

import br.com.dio.app.repositories.core.RemoteException
import br.com.lfcapp.portfliodogithub.data.services.GitHubService
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class RepoRepositoryImpl(private val service: GitHubService) : RepoRepository {

    override suspend fun listRepositories(user: String) = flow {
        try {
            val repoList = service.listRepos(user)
            emit(repoList)
        } catch (ex: HttpException) {
            throw RemoteException(ex.message ?: "Houve Erro Desconhecido")
        }

    }
}