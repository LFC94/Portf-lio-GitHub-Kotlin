package br.com.lfcapp.portfliodogithub.data.di

import br.com.lfcapp.portfliodogithub.data.repositories.RepoRepositoryImpl
import br.com.lfcapp.portfliodogithub.data.repositories.RepoRepository
import br.com.lfcapp.portfliodogithub.data.services.GitHubService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModel {

    private const val URL_BASE = "https://api.github.com/"

    fun load() {
        loadKoinModules(networkModules() + repositoriesModules())
    }

    private inline fun <reified T> createService(
        client: OkHttpClient,
        factory: GsonConverterFactory
    ): T {
        return Retrofit.Builder().baseUrl(URL_BASE)
            .client(client)
            .addConverterFactory(factory)
            .build().create(T::class.java)
    }

    private fun repositoriesModules(): Module {
        return module {
            single<RepoRepository> { RepoRepositoryImpl(get()) }
        }
    }

    private fun networkModules(): Module {
        return module {
            single {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                OkHttpClient.Builder().addInterceptor(interceptor).build()
            }
            single {
                GsonConverterFactory.create(GsonBuilder().create())
            }
            single {
                createService<GitHubService>(get(), get())
            }
        }
    }

}