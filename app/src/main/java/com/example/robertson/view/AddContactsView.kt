package com.example.robertson.view

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.robertson.data.ContactType
import com.example.robertson.ui.DarkColorScheme
import com.example.robertson.ui.LightColorScheme
import com.example.robertson.ui.orangeDark
import com.example.robertson.ui.orangeLight
import com.example.robertson.viewmodel.ContactsViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddContactScreen() {

    val colorScheme = if (isSystemInDarkTheme()) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme
    ) {

        //Get viewmodel
        val contactsViewModel: ContactsViewModel = koinViewModel()

        //Get contacts from repository
        val contacts = contactsViewModel.contacts.collectAsState()

        //Initialize contact variables
        var name by remember { mutableStateOf("") }
        var phone by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        val contactType = remember { mutableStateOf(ContactType.None) }

        //Setup snackbar
        val scope = rememberCoroutineScope()
        val snackbarHostState = remember { SnackbarHostState() }

        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            }) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
            ) {
                //Name Field
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                //Phone Field
                TextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Phone Number") },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                //Email Field
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                //Select Contact Type
                CheckboxWithLabel(contactType, "Friend", ContactType.Friend)
                CheckboxWithLabel(contactType, "Family", ContactType.Family)
                CheckboxWithLabel(contactType, "Work", ContactType.Work)

                //Add Button
                Button(modifier = Modifier.padding(10.dp),
                    onClick = {
                        //Add Contact to repo
                        contactsViewModel.addContact(name, phone, email, contactType.value)

                        //Activate snackbar
                        scope.launch {
                            snackbarHostState.showSnackbar("Contact $name added!")
                        }
                    }) {
                    Text("Add Contact")
                }

                // Display list of added contacts
                LazyColumn(
                    modifier = Modifier
                        .background(
                            colorScheme.tertiary
                        )
                        .fillMaxHeight()
                ) {
                    items(contacts.value) { contact ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = colorScheme.secondary
                            )
                        ) {
                            //Show contact data
                            Column(
                                modifier = Modifier
                                    .padding(20.dp))
                            {
                                Text(text = contact.name, fontSize = 30.sp)
                                Text(text = contact.phone, fontSize = 15.sp)
                                Text(text = contact.email, fontSize = 15.sp)
                                Text(
                                    text = "Relation: " + contact.type.toString(),
                                    fontSize = 15.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CheckboxWithLabel(
    selectedContactType: MutableState<ContactType>,
    label: String,
    type: ContactType
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = selectedContactType.value == type,
            onCheckedChange = { selectedContactType.value = type }
        )
        Text(label)
    }
}