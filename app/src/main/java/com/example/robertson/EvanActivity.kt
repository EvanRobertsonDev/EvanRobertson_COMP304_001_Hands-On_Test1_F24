package com.example.robertson

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.robertson.di.appModules
import com.example.robertson.view.MainActivityUI
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

//Student No: 301291019
class EvanActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            androidContext(this@EvanActivity)
            modules(appModules)
        }

        setContent {
            MainActivityUI (onNavigateToContactActivity = {
                //Navigate to second activity
                val intent = Intent(this, RobertsonActivity::class.java)
                startActivity(intent)
            })
        }
    }
}