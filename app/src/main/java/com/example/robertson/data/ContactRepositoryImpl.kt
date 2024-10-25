package com.example.robertson.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

//Implementing ContactRepository Interface
class ContactRepositoryImpl : ContactRepository {

    //Create StateFlow list for contacts
    private val contacts = MutableStateFlow<List<Contact>>(emptyList())

    //Returns stateflow list of contacts
    override fun getContacts(): StateFlow<List<Contact>> {
        return contacts.asStateFlow()
    }

    //Adds contact to stateflow list
    override fun addContact(contact: Contact) {
        contacts.value += contact
    }
}