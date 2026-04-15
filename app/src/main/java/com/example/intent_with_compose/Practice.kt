package com.example.intent_with_compose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.intent_with_compose.rate
import com.example.intent_with_compose.ui.theme.Intent_with_composeTheme
import kotlinx.coroutines.delay

class Practice : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Intent_with_composeTheme {
                var showSplash by remember { mutableStateOf(true) }

                if (showSplash) {
                    SplashScreen(onTimeout = { showSplash = false })
                } else {
                    MainScreen(emojis = emojis)
                }
            }
        }
    }
}

val emojis = listOf(
    "😀", "😃", "😄", "😁", "😆", "😅", "😂", "🤣", "😊", "😇",
    "🙂", "🙃", "😉", "😍", "🥰", "😘", "😜", "🤪", "😝", "🤗",
    "🤭", "🤔", "🤨", "😐", "😑", "😶", "🙄", "😬", "🤥", "😌",
    "😔", "😪", "🤤", "😴", "😷", "🤒", "🤕", "🤢", "🤮", "😎",
    "🤓", "🥳", "😤", "😡", "😱", "🤠", "🤡", "👻", "💀", "👽"
)

@Composable
fun MainScreen(emojis: List<String>) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Greeting(
            modifier = Modifier.padding(innerPadding),
            emojis = emojis
        )
    }
}

@Composable
fun Card(item: String) {
    val colors = remember {
        listOf(
            Color.Blue, Color.Red, Color.Green, Color.Yellow,
            Color.Black, Color.Gray, Color.Magenta, Color.Cyan,
            Color.DarkGray, Color.LightGray
        )
    }
    val backgroundColor = remember { colors.random() }

    Box(
        modifier = Modifier
            .size(120.dp)
            .padding(10.dp)
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(text = item, fontSize = 50.sp)
    }
}

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(3000)
        onTimeout()
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
        Text(text = "Splash Screen", fontSize = 50.sp)
    }
}
val rate=listOf("😡","😕","😐","🙂","😍")
val textrating=listOf("Very Bad","Bad","Normal","Good","Excellent")
@Composable
fun RatingBar(rating: Int, rate:List<String>,onRatingChanged:(Int)->Unit){
    val context = LocalContext.current
Row(){
    for(i in 1..5) {
        Text(
            text = rate[i - 1],
            fontSize = if (i <=rating) 40.sp else 30.sp,

            modifier = Modifier.padding(if(i<rating)5.dp else 10.dp).clickable(onClick = { onRatingChanged(i) })
        )
    }


}
    if (rating > 0) {
        Text(text = textrating[rating - 1], fontSize = 30.sp)
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier, emojis: List<String>) {

    var rating by remember { mutableStateOf(0) }
    Column(modifier=modifier.fillMaxSize(),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        RatingBar(rating = rating, rate = rate, onRatingChanged = { rating = it })
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    Intent_with_composeTheme {
        Greeting(emojis = emojis)
    }
}
