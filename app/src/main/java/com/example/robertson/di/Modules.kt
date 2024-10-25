package com.example.robertson.di

import com.example.robertson.data.ContactRepositoryImpl
import com.example.robertson.viewmodel.ContactsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module

val appModules = module {
    single<ContactRepositoryImpl> { ContactRepositoryImpl() }
    viewModel { ContactsViewModel(get()) }
}