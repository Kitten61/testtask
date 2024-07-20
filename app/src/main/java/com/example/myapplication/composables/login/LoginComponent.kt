package com.example.myapplication.composables.login

import com.example.myapplication.di.AppComponent
import dagger.Component
import javax.inject.Scope

@Component(
    dependencies = [AppComponent::class]
)
@LoginScope
interface LoginComponent {

    @Component.Factory
    interface Factory {

        fun create(appComponent: AppComponent): LoginComponent
    }

    @LoginScope
    fun getViewModel(): LoginViewModel
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class LoginScope