package com.jhainusa.happypulse

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jhainusa.happypulse.databinding.ActivityMakeYourProfileBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MakeYourProfile : AppCompatActivity() {
    private lateinit var binding: ActivityMakeYourProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMakeYourProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spinner:Spinner=binding.gender
        val options = listOf("Male","Female","Not Specify")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        val spinner2:Spinner = binding.bloodgroup
        val bgs = listOf("A+","A-","B+","B-","AB+","AB-","O+","0-","Not Specified")
        val adapter2 =ArrayAdapter(this,android.R.layout.simple_spinner_item,bgs)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2.adapter=adapter2

        binding.completeProfileButton.setOnClickListener {
            val name =binding.name.text.toString()
            val age =binding.age.text.toString()
            val gender =binding.gender.selectedItem.toString()
            val weight =binding.weight.text.toString()
            val height =binding.height.text.toString()
            val bloodgrp=binding.bloodgroup.selectedItem.toString()
            if(name.isEmpty() || age.isEmpty()|| gender.isEmpty()||weight.isEmpty()||height.isEmpty()){
                Toast.makeText(this,"Please fill all the details",Toast.LENGTH_SHORT).show()
            }
            else{
                    val editor = getSharedPreferences("USER_DETAILS", MODE_PRIVATE).edit()
                    editor.putString("name",name)
                    editor.putString("age",age)
                    editor.putString("gender",gender)
                    editor.putString("weight",weight)
                    editor.putString("height",height)
                    editor.putString("bloodgrp",bloodgrp)
                    Toast.makeText(this@MakeYourProfile,"SharedPreference Created",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,MainPage::class.java))
            }
        }
    }
}