package com.example.intent_with_compose

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.intent_with_compose.ui.theme.Intent_with_composeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Intent_with_composeTheme {
                MainScreen(context = LocalContext.current)
            }
        }
    }
}

@Composable
fun MainScreen(context: Context){
    Column(
        modifier=Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(onClick = {
            val intent= Intent(context, Second_Activity::class.java)
            context.startActivity(intent)
        }){
            Text(text = "Go to Second Activity")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Intent_with_composeTheme {
        MainScreen(context = LocalContext.current)

    }
}