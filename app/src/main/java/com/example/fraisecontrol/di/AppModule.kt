package com.example.fraisecontrol.di

import android.content.Context
import com.example.fraisecontrol.network.MqttRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideMqttRepo(@ApplicationContext context : Context):MqttRepository = MqttRepository(context)


}