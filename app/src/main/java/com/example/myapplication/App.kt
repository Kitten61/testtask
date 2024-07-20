package com.example.myapplication

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import com.example.myapplication.di.AppComponent
import com.example.myapplication.di.DaggerAppComponent
import java.util.prefs.Preferences

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .context(this)
            .build()
    }
}

fun Context.appComponent(): AppComponent {
    return if (this is App) {
        appComponent
    } else {
        this.applicationContext.appComponent()
    }
}

