package br.com.lfcapp.portfliodogithub.ui

import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import br.com.lfcapp.portfliodogithub.R
import br.com.lfcapp.portfliodogithub.core.createDialog
import br.com.lfcapp.portfliodogithub.core.createProgressDialog
import br.com.lfcapp.portfliodogithub.core.hideSoftKeyboard
import br.com.lfcapp.portfliodogithub.databinding.ActivityMainBinding
import br.com.lfcapp.portfliodogithub.presentation.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private val dialog by lazy { createProgressDialog() }
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val adapter by lazy { RepoListAdapter() }
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        viewModel.getRepoList("LFC94")
        binding.rvList.adapter = adapter
        viewModel.repos.observe(this) {
            when (it) {
                MainViewModel.State.Loading -> {
                    dialog.show()
                }
                is MainViewModel.State.Erro -> {
                    dialog.dismiss()
                    createDialog {
                        setMessage(it.error.message)
                    }.show()
                }
                is MainViewModel.State.Success -> {
                    dialog.dismiss()
                    if (it.list.size >= 1) {
                        adapter.submitList(it.list)
                    } else {
                        createDialog {
                            setMessage("Nenhum usuario Localizado")
                        }.show()
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)
        var searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let { viewModel.getRepoList(it) }
        binding.root.hideSoftKeyboard()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}

