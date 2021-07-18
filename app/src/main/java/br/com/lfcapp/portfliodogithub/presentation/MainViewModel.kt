package br.com.lfcapp.portfliodogithub.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lfcapp.portfliodogithub.data.model.Repo
import br.com.lfcapp.portfliodogithub.domain.ListUserRepositorisUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel(private val useCase: ListUserRepositorisUseCase) : ViewModel() {

    private val _repo = MutableLiveData<State>()
    val repos: LiveData<State> = _repo

    fun getRepoList(user: String) {
        viewModelScope.launch {
            useCase(user)
                .onStart {
                    _repo.postValue(State.Loading)
                }
                .catch {
                    _repo.postValue(State.Erro(it))
                }
                .collect {
                    Log.i("Retorno", it.toString())
                    _repo.postValue(State.Success(it))
                }
        }
    }

    sealed class State {
        object Loading : State()
        data class Success(val list: List<Repo>) : State()
        data class Erro(val error: Throwable) : State()
    }
}