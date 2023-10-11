package com.example.pract22

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar

import org.json.JSONObject
import org.w3c.dom.Text

private val key:String = "5f09ba8df7d9e265c1f42037c16e07b8"
lateinit var city: EditText
lateinit var temperature: TextView
lateinit var wind:TextView
lateinit var cityTextView: TextView
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        city = findViewById(R.id.cityEditText)
        wind = findViewById(R.id.wind)
        temperature = findViewById(R.id.temperature)
        cityTextView = findViewById(R.id.cityTextView   )
    }
    private fun getResult(city:String){


    }

    fun showWeather(view: View) {
        if(city.text.isNullOrEmpty()){
            Snackbar.make(view,"Вы не ввели город",Snackbar.LENGTH_LONG).show()
            return
        }
        var url="https://api.openweathermap.org/data/2.5/weather?q=${city.text}&appid=$key&units=metric&lang=ru"
        val queue = Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url,null,
            { response ->
                val main = response.getJSONObject("main")
                val windObject = response.getJSONObject("wind")
                cityTextView.text = "Город: ${response.getString("name")}"
                temperature.text = "Температура: ${main.getString("temp")}"
                wind.text = "Скорость ветра: ${windObject.getString("speed")} м/c"

            },
            {
                Snackbar.make(view,"Такого города нет",Snackbar.LENGTH_LONG).show()
            }
        )
        queue.add(jsonObjectRequest)
    }
}