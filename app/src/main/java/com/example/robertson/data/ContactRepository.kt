package com.example.robertson.data

import kotlinx.coroutines.flow.StateFlow

interface ContactRepository {
    fun getContacts() : StateFlow<List<Contact>>
    fun addContact(contact : Contact)
}