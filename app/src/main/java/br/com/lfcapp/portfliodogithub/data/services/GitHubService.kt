package br.com.lfcapp.portfliodogithub.data.services

import br.com.lfcapp.portfliodogithub.data.model.Repo
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("users/{user}/repos?type=all&per_page=100")
    suspend fun listRepos(@Path("user") user: String?): List<Repo>
}