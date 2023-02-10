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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
The main issue was that I couldn't evoke the Modifier.weight() method within a Composable function
You can only use the Modifier.weight if it's nested within a parent scope.
If it's OUTSIDE THE ORIGINAL SCOPE, you need to pass it as an argument.
    ***!!! You need to pass a modifier argument WITHIN THE PARENT'S SCOPE if it's the last composable
ERROR thrown:
Cannot access 'ColumnScopeInstance': it is internal in 'androidx.compose.foundation.layout'


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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ){
        Row(Modifier.weight(1f)) {
            Quadrant(
                title = stringResource( id = R.string.quadrant1_title ),
                description = stringResource( id = R.string.quadrant1_description),
                backgroundColor = Color.Green,
                modifier = Modifier.weight(1f)
            )
            Quadrant(
                title = stringResource( id = R.string.quadrant2_title ),
                description = stringResource( id = R.string.quadrant2_description),
                backgroundColor = Color.Yellow,
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            Quadrant(
                title = stringResource( id = R.string.quadrant3_title ),
                description = stringResource( id = R.string.quadrant3_description),
                backgroundColor = Color.Cyan,
                modifier = Modifier.weight(1f)
            )
            Quadrant(
                title = stringResource( id = R.string.quadrant4_title ),
                description = stringResource( id = R.string.quadrant4_description),
                backgroundColor = Color.LightGray,
                modifier = Modifier.weight(1f)
            )
        }

    }
}

@Composable
fun Quadrant(title:String, description:String, backgroundColor:Color , modifier: Modifier){
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold, 
            modifier = Modifier.padding(bottom = 16.dp) )
        Text(
            text = description, textAlign = TextAlign.Justify)
        
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