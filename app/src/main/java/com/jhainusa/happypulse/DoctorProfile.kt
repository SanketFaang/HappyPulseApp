package com.jhainusa.happypulse

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jhainusa.happypulse.databinding.ActivityDoctorProfileBinding
import com.squareup.picasso.Picasso
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

class DoctorProfile : AppCompatActivity() {
    private lateinit var binding: ActivityDoctorProfileBinding
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityDoctorProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val docname = intent.getStringExtra("DoctorName")
        val docbio = intent.getStringExtra("Doctor Bio")
        val photo = intent.getStringExtra("photo")
        val docexp = intent.getStringExtra("docexp")

        val img = binding.circleImageView5
        Picasso.get().load(photo).into(img)
        binding.textView9.text=docname
        binding.textView10.text=docexp
        binding.textView12.text=docbio

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val currentDate = LocalDate.now()
        val monthName = currentDate.month.getDisplayName(TextStyle.SHORT, Locale.getDefault())
        val year=currentDate.year
        var date = currentDate.dayOfMonth.toInt()
        var dayOfWeek = currentDate.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
        binding.textView21.setText(monthName+" "+year)

        val dates = listOf<TextView>(
            binding.textView14,
            binding.textView15,
            binding.textView16,
            binding.textView17,
            binding.textView18,
            binding.textView19
            )
        dates.forEach{ dates ->
            dates.setText(""+date)
            date++;
        }
        val day = listOf<TextView>(
            binding.day1,
            binding.day2,
            binding.day3,
            binding.day4,
            binding.day5,
            binding.day6
            )
        var t:Int=1
        day.forEach{ day->
            day.setText(dayOfWeek)
            dayOfWeek=currentDate.plusDays(t.toLong()).dayOfWeek.getDisplayName(TextStyle.SHORT,Locale.getDefault())
            t++;
        }



        val time1 = arrayOf("9:30 AM","10:15 AM","11:00 AM","11:45 AM")
        val time2 = arrayOf("2:00 PM","2:45 PM","3:30 PM","4:15 PM")
        val time3 = arrayOf("5:00 PM","5:45 PM","6:30 PM","7:15 PM")
        binding.morning.setOnClickListener{
            binding.textView25.setText("Morning Schedule")
            binding.time1.setText(time1[0])
            binding.time2.setText(time1[1])
            binding.time3.setText(time1[2])
            binding.time4.setText(time1[3])
            binding.textView25.visibility= View.VISIBLE
            binding.linearLayout2.visibility = View.VISIBLE
        }

        binding.afternoon.setOnClickListener{
            binding.textView25.setText("Afternoon Schedule")
            binding.time1.setText(time2[0])
            binding.time2.setText(time2[1])
            binding.time3.setText(time2[2])
            binding.time4.setText(time2[3])
            binding.textView25.visibility= View.VISIBLE
            binding.linearLayout2.visibility = View.VISIBLE
        }

        binding.evening.setOnClickListener{
            binding.textView25.setText("Evening Schedule")
            binding.time1.setText(time3[0])
            binding.time2.setText(time3[1])
            binding.time3.setText(time3[2])
            binding.time4.setText(time3[3])
            binding.textView25.visibility= View.VISIBLE
            binding.linearLayout2.visibility = View.VISIBLE
        }

    }
}