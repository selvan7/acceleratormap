package com.testo.acceleratormaplibrary

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri

class Map {
	companion object {
		fun commonDrawMapTrack(source: String, destination: String, context: Context) {
			try {
				val uri: Uri = Uri.parse("https://www.google.co.in/maps/dir/$source/$destination")
				val i = Intent(Intent.ACTION_VIEW, uri)
				// below line is to set maps package name
				i.setPackage("com.google.android.apps.maps")
				i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
				context.startActivity(i)
			} catch (e: ActivityNotFoundException) {
				// when the google maps is not installed on user's device
				// we will redirect our user to google play to download google maps.
				val uri: Uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps")
				val i = Intent(Intent.ACTION_VIEW, uri)
				i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
				context.startActivity(i)
			}
		}
	}
}