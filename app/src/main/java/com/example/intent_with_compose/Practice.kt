package com.example.intent_with_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.intent_with_compose.ui.theme.Intent_with_composeTheme

class Practice : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Intent_with_composeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                        ,emojis=emojis
                    )
                }
            }
        }
    }
}
val emojis = listOf(
    "😀","😃","😄","😁","😆","😅","😂","🤣","😊","😇",
    "🙂","🙃","😉","😍","🥰","😘","😜","🤪","😝","🤗",
    "🤭","🤔","🤨","😐","😑","😶","🙄","😬","🤥","😌",
    "😔","😪","🤤","😴","😷","🤒","🤕","🤢","🤮","😎",
    "🤓","🥳","😤","😡","😱","🤠","🤡","👻","💀","👽"
)
@Composable
fun Card(Item:String){
    val colors=listOf(
        androidx.compose.ui.graphics.Color.Blue,
        androidx.compose.ui.graphics.Color.Red,
        androidx.compose.ui.graphics.Color.Green,
        androidx.compose.ui.graphics.Color.Yellow,
        androidx.compose.ui.graphics.Color.Black,
        androidx.compose.ui.graphics.Color.Gray,
        androidx.compose.ui.graphics.Color.Magenta,
        androidx.compose.ui.graphics.Color.Cyan,
        androidx.compose.ui.graphics.Color.DarkGray,
        androidx.compose.ui.graphics.Color.LightGray,
        androidx.compose.ui.graphics.Color.Transparent
    )
    Box(modifier=Modifier.size(120.dp).padding(20.dp).background(colors.random()),contentAlignment = androidx.compose.ui.Alignment.Center){
        Text(text=Item)
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier,emojis:List<String>) {
    LazyColumn() {
        items(emojis){item->
            Card(item)
        }
    }
}

@Preview(showBackground = true,showSystemUi=true)
@Composable
fun GreetingPreview() {
    Intent_with_composeTheme {
        Greeting("Android",emojis=emojis)
    }
}