package com.example.myapplication.composables.check_code

import com.example.myapplication.di.AppComponent
import dagger.Component
import javax.inject.Scope

@Component(
    dependencies = [AppComponent::class]
)
@LoginScope
interface CheckCodeComponent {

    @Component.Factory
    interface Factory {

        fun create(appComponent: AppComponent): CheckCodeComponent
    }

    @LoginScope
    fun getViewModel(): CheckCodeViewModel
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class LoginScope