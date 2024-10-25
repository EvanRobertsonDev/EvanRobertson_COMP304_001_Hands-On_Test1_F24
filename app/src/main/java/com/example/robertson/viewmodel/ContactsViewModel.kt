package com.example.robertson.viewmodel

import androidx.lifecycle.ViewModel
import com.example.robertson.data.Contact
import com.example.robertson.data.ContactRepositoryImpl
import com.example.robertson.data.ContactType
import kotlinx.coroutines.flow.StateFlow

class ContactsViewModel(
    private val repository: ContactRepositoryImpl
) : ViewModel() {

    //Pull contacts from repo
    val contacts : StateFlow<List<Contact>> = repository.getContacts()

    //Add new contact to repo
    fun addContact(name : String, phone : String, email : String, type : ContactType) {
        val contact = Contact(contacts.value.size, name, phone, email, type)

        repository.addContact(contact);
    }
}