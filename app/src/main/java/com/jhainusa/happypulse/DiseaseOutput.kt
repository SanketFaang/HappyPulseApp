package com.jhainusa.happypulse

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DiseaseOutput : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_disease_output)
        val data = intent.getStringExtra("result")
        val txt = findViewById<TextView>(R.id.tv_suffering)
        txt.text="You are suffering from \n ${data}"
    }
}