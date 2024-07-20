package com.example.myapplication.di.modules

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides

@Module(includes = [UseCaseModule::class])
class AppModule {

    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE)
    }
}
