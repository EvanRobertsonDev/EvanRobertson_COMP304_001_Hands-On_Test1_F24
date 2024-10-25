package com.example.robertson.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.evanrobertson_comp304_001_hands_on_test1_f24.R
import com.example.robertson.ui.DarkColorScheme
import com.example.robertson.ui.LightColorScheme

@Composable
fun MainActivityUI(onNavigateToContactActivity: () -> Unit) {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) DarkColorScheme else LightColorScheme
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFB800)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Company Logo"
            )


            Spacer(modifier = Modifier.height(32.dp))

            // Image Button
            IconButton(
                onClick = onNavigateToContactActivity,
                modifier = Modifier.size(128.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    tint = Color.White,
                    modifier = Modifier.size(64.dp),
                    contentDescription = "Add Contacts"
                )

            }
            Text(fontSize = 20.sp, text = "Add Contacts", color = Color.White)
        }
    }
}