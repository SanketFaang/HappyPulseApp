package com.jhainusa.happypulse

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jhainusa.happypulse.databinding.ActivityConfirmYouAreReadyBinding
import com.jhainusa.happypulse.databinding.ActivityMainPageBinding

class Confirm_you_are_ready : AppCompatActivity() {
    private lateinit var binding : ActivityConfirmYouAreReadyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_confirm_you_are_ready)
        val btn= findViewById<Button>(R.id.continue_button)
        btn.setOnClickListener {
            startActivity(Intent(this@Confirm_you_are_ready,MakeYourProfile::class.java))
        }

    }
}