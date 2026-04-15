package com.example.intent_with_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.intent_with_compose.ui.theme.Intent_with_composeTheme
import kotlinx.coroutines.delay

class music_app_practice : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Intent_with_composeTheme {
               var Splash by remember { mutableStateOf(true) }
                if(Splash){
                    splashScreen(onTimeout={Splash=false})
                }
                else{
                    MusicApp()
                }
            }
        }
    }
}

@Composable
fun splashScreen(onTimeout:()->Unit){
    LaunchedEffect(Unit){
        delay(3000)
        onTimeout()
    }
    Column(modifier=Modifier.fillMaxSize(),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter=painterResource(id=R.drawable.lpu_logo),
            contentDescription="logo"
        )
        Text("Vertos of Lovely Professional University",modifier=Modifier.padding(10.dp))

    }

}
val ratingEmoji=listOf("😡","😕","😐","🙂","😍");
@Composable
fun Ratingbar(rating:Int,onRatingChanged:(Int)->Unit){
    Row(){
        for(i in 1..5){
            Text(text=ratingEmoji[i-1],
                fontSize = if(i<=rating) 20.sp else 10.sp,
                modifier = Modifier.padding(if(i<=rating) 5.dp else 10.dp).clickable(onClick={onRatingChanged(i)})
            )
        }
    }
}
@Composable
fun card(s:Int,a:Int,Songs:List<String>,Artists:List<String>){
    Box(modifier=Modifier.fillMaxSize(),contentAlignment = Alignment.Center){
        Column(){
            var rating by remember { mutableStateOf(0) }
            Text(Songs[s])
            Text(Artists[a])
            Ratingbar(rating=rating,onRatingChanged = {rating=it})
        }
    }
}
@Composable
fun MusicApp(){
    val songs=listOf("song1","song2","song3","song4","song5")
    val artists=listOf("artist1","artist2","artist3","artist4","artist5")
    LazyVerticalGrid(columns= GridCells.Fixed(1),modifier=Modifier.fillMaxSize(),verticalArrangement = Arrangement.Center,horizontalArrangement = Arrangement.Center){
        items(songs.size){i->
            card(i,i,songs,artists)
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview2() {
    Intent_with_composeTheme {
        MusicApp()

    }
    }
