package com.example.myapplication.composables.registration

import com.example.myapplication.di.AppComponent
import dagger.Component
import javax.inject.Scope

@Component(
    dependencies = [AppComponent::class]
)
@RegistrationScope
interface RegistrationComponent {

    @Component.Factory
    interface Factory {

        fun create(appComponent: AppComponent): RegistrationComponent
    }

    @RegistrationScope
    fun getViewModel(): RegistrationViewModel
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class RegistrationScope