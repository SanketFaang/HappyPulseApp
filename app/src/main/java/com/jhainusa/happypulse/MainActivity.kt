package com.jhainusa.happypulse

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.jhainusa.happypulse.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btn = findViewById<AppCompatButton>(R.id.LogininButton)
        btn.setOnClickListener {
            startActivity(Intent(this@MainActivity,LoginActivity::class.java))

        }
        val btn2 = findViewById<AppCompatButton>(R.id.CreateYourAccountButton)
        btn2.setOnClickListener {
            startActivity(Intent(this@MainActivity,Create_your_acc::class.java))

        }
        val viewPager:ViewPager2 =findViewById(R.id.viewPager)
        val tabLayout:TabLayout=findViewById(R.id.tabLayout)

        val images = listOf(
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img5
        )
       val adapter=MyAdapter(images)
       viewPager.adapter=adapter
     TabLayoutMediator(tabLayout,viewPager){ _,_ ->
       }.attach()
    }
}