package com.example.robertson

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.robertson.view.AddContactScreen


//Student No: 301291019
class RobertsonActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AddContactScreen()
        }
    }
}