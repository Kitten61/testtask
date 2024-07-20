package com.example.myapplication.composables.main_activity

import com.example.myapplication.di.AppComponent
import dagger.Component
import javax.inject.Scope

@Component(
    dependencies = [AppComponent::class]
)
@MainScope
interface MainComponent {

    @Component.Factory
    interface Factory {

        fun create(appComponent: AppComponent): MainComponent
    }

    @MainScope
    fun getViewModel(): MainViewModel
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class MainScope