package com.testo.mapaccelerator

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.testo.mapaccelerator.ui.theme.MapAcceleratorTheme

class MapActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MapAcceleratorTheme {
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					MapUI(LocalContext.current)
				}
			}
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapUI(context: Context) {
	// on below line we are creating variable for source and destination location
	val sourceLocation = remember {
		mutableStateOf("")
	}
	val destinationLocation = remember {
		mutableStateOf("")
	}
	// on below line creating a variable for location.
	// on below line creating a column for our maps.
	Column(
		modifier = Modifier
			.fillMaxHeight()
			.fillMaxWidth()
			.background(Color.White)
	) {
		// on below line we are adding a spacer.
		Spacer(modifier = Modifier.height(20.dp))
		// on below line we are adding a text
		Text(
			text = "Draw Route on Google Maps in Android",
			textAlign = TextAlign.Center,
			color = Color.Green,
			fontWeight = FontWeight.Bold,
			modifier = Modifier
				.padding(10.dp)
				.fillMaxWidth()
		)
		// on below line we are creating a text field for our message number.
		TextField(
			// on below line we are specifying value for our message text field.
			value = sourceLocation.value,
			// on below line we are adding on value change for text field.
			onValueChange = { sourceLocation.value = it },
			// on below line we are adding place holder as text as "Enter your email"
			placeholder = { Text(text = "Enter your start location") },
			// on below line we are adding modifier to it and adding padding to it and filling max width
			modifier = Modifier.padding(15.dp).fillMaxWidth(),
			// on below line we are adding text style specifying color and font size to it.
			textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
			//on below line we are adding single line to it.
			singleLine = true,
		)

		// on below line we are adding a spacer.
		Spacer(modifier = Modifier.height(10.dp))

		// on below line we are adding a text field to add destination location.
		// on below line we are creating a text field for our message number.
		TextField(
			// on below line we are specifying value for our message text field.
			value = destinationLocation.value,
			// on below line we are adding on value change for text field.
			onValueChange = { destinationLocation.value = it },
			// on below line we are adding place holder as text as "Enter your email"
			placeholder = { Text(text = "Enter your destination location") },
			// on below line we are adding modifier to it and adding padding to it and filling max width
			modifier = Modifier.padding(15.dp).fillMaxWidth(),
			// on below line we are adding text style specifying color and font size to it.
			textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
			//on below line we are adding single line to it.
			singleLine = true,
		)

		// on below line we are adding a spacer.
		Spacer(modifier = Modifier.height(20.dp))

		// on below line adding a button.
		Button(
			onClick = {
				commonDrawMapTrack(sourceLocation.value, destinationLocation.value, context)
			},
			// on below line adding a modifier for our button.
			modifier = Modifier.padding(10.dp).fillMaxWidth()
		) {
			// on below line we are adding a text
			Text(
				text = "Draw Route on Google Maps",
				color = Color.White,
				textAlign = TextAlign.Center
			)
		}
	}
}

private fun commonDrawMapTrack(source: String, destination: String, context: Context) {
	try {
		val uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/$source/$destination")
		val i = Intent(Intent.ACTION_VIEW, uri)
		// below line is to set maps package name
		i.setPackage("com.google.android.apps.maps")
		i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
		context.startActivity(i)
	} catch (e: ActivityNotFoundException) {
		// when the google maps is not installed on users device
		// we will redirect our user to google play to download google maps.
		val uri: Uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps")
		val i = Intent(Intent.ACTION_VIEW, uri)
		i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
		context.startActivity(i)
	}
}