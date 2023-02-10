package com.example.androidcomposeassignment3

import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidcomposeassignment3.ui.theme.AndroidComposeAssignment3Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val screenDimensions = getScreenDimensions(windowManager)
        val width = screenDimensions.x
        val height = screenDimensions.y
        Log.d("TAG", "SIZE: $screenDimensions !!!")


        setContent {
            AndroidComposeAssignment3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    QuadrantApp()
                }
            }
        }
    }
}


/*
Lessons learned:
android:layout_width="match_parent" == Modifier.fillMaxWidth()
android:layout_height="match_parent" == Modifier.fillMaxHeight()
android:layout_(width AND height) == Modifier.fillMaxSize()

Remember, a column or row needs to be 'expanded' or 'inflated' accordingly
    - The primary parent layout (column) needed to fill out the whole screen
        - you could use just .fillMaxWidth or .fillMaxSize()
    - The secondary layout (two nested rows) need to be weighted evenly to get to 50%.
        - in other words 1f to 1f, 50f to 50f, etc...
    - The tertiary layout (4 columns(quadrants)) need to FULLY expand into the parent ('match_parent')
 */


@Composable
private fun QuadrantApp() {
    Column(Modifier.fillMaxSize().background(Color.LightGray)){
        Row(Modifier.weight(1f)) {
            Column(Modifier.weight(1f).background(Color.Red).fillMaxSize()){

            }
            Column(Modifier.weight(1f).background(Color.Green).fillMaxSize()){

            }

        }
        Row(Modifier.weight(1f)) {
            Column(Modifier.weight(1f).background(Color.Blue).fillMaxSize()){

            }
            Column(Modifier.weight(1f).background(Color.Black).fillMaxSize()){

            }
        }

    }
}

fun getScreenDimensions(windowManager: WindowManager): Point{
    val display = windowManager.defaultDisplay
    val size = Point()
    display.getSize(size)
    return size
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndroidComposeAssignment3Theme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            QuadrantApp()
        }
    }
}