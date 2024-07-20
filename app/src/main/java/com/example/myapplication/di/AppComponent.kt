package com.example.myapplication.di

import android.content.Context
import android.content.SharedPreferences
import com.example.myapplication.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Component(modules = [AppModule::class])
@Singleton
interface AppComponent : Repositories, UseCases {

    fun sharedPreferences(): SharedPreferences

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}