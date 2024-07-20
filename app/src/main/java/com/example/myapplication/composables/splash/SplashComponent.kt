package com.example.myapplication.composables.splash

import com.example.myapplication.di.AppComponent
import dagger.Component
import javax.inject.Scope

@Component(
    dependencies = [AppComponent::class]
)
@SplashScope
interface SplashComponent {

    @Component.Factory
    interface Factory {

        fun create(appComponent: AppComponent): SplashComponent
    }

    @SplashScope
    fun getViewModel(): SplashViewModel
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class SplashScope