package com.sunnyweather.android

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import java.util.Properties
import kotlin.math.log

class SunnyWeatherApplication : Application() {
    companion object {
        const val tag = "SunnyWeatherApplication"
        lateinit var WeatherToken: String
        lateinit var MapToken: String
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        val resources = context.resources
        val inputStream = resources.openRawResource(R.raw.config)
        val properties = Properties()
        properties.load(inputStream)
        WeatherToken = properties.getProperty("weather.token")
        MapToken = properties.getProperty("map.token")
        Log.i(tag,"WeatherToken:${WeatherToken}")
        Log.i(tag,"MapToken:${MapToken}")
    }
}