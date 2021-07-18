package br.com.lfcapp.portfliodogithub.data.repositories

import br.com.lfcapp.portfliodogithub.data.model.Repo
import kotlinx.coroutines.flow.Flow

interface RepoRepository {
    suspend fun listRepositories(user: String): Flow<List<Repo>>
}