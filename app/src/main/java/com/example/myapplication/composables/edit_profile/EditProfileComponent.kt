package com.example.myapplication.composables.edit_profile

import com.example.myapplication.composables.login.LoginViewModel
import com.example.myapplication.di.AppComponent
import dagger.Component
import javax.inject.Scope

@Component(
    dependencies = [AppComponent::class]
)
@EditProfileScope
interface EditProfileComponent {

    @Component.Factory
    interface Factory {

        fun create(appComponent: AppComponent): EditProfileComponent
    }

    @EditProfileScope
    fun getViewModel(): EditProfileViewModel
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class EditProfileScope