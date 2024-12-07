package com.jhainusa.happypulse

import android.app.Activity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jhainusa.happypulse.databinding.ActivityMainPageBinding
import com.jhainusa.happypulse.doctor_appointment.AppointmentFragment
import com.jhainusa.happypulse.doctor_appointment.ChatbotFragment
import com.jhainusa.happypulse.doctor_appointment.HomeFragment
import com.jhainusa.happypulse.doctor_appointment.SettingFragment
import com.jhainusa.happypulse.doctor_appointment.Symptoms
import com.jhainusa.happypulse.doctor_appointment.doctorinfo
import np.com.susanthapa.curved_bottom_navigation.CbnMenuItem

class MainPage : AppCompatActivity() {
    private lateinit var binding : ActivityMainPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPageBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val menuItems = arrayOf(
            CbnMenuItem(
                R.drawable.noun_chatbot_1596689, R.drawable.animator_chatbot
            ),
            CbnMenuItem(
                R.drawable.stethoscope_icon, R.drawable.animator_symptoms
            ),
            CbnMenuItem(
                R.drawable.home_icon, R.drawable.animator_home
            ),
            CbnMenuItem(
                R.drawable.doctor_icon,
                R.drawable.animated_doctor
            ),
            CbnMenuItem(
                R.drawable.baseline_settings_24, R.drawable.animated_settings
            )
        )
        binding.navView.setMenuItems(menuItems, 2)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,HomeFragment())
            .commit()
        binding.navView.setOnMenuItemClickListener { cbnMenuItem, index ->
            val fragment = when (index) {
                0 -> ChatbotFragment()
                1 -> Symptoms()
                2 -> HomeFragment()
                3 -> AppointmentFragment()
                4 -> SettingFragment()
                else -> HomeFragment()
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        }
    }
}