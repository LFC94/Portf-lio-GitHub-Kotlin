package br.com.lfcapp.portfliodogithub

import android.app.Application
import br.com.lfcapp.portfliodogithub.data.di.DataModel
import br.com.lfcapp.portfliodogithub.domain.di.DomainModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
        }

        DataModel.load()
        DomainModel.load()

    }
}