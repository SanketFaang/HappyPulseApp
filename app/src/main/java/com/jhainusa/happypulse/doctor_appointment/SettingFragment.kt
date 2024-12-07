package com.jhainusa.happypulse.doctor_appointment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jhainusa.happypulse.R
import com.jhainusa.happypulse.databinding.ActivityDoctorProfileBinding
import com.jhainusa.happypulse.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {
private lateinit var binding: FragmentSettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentSettingBinding.inflate(layoutInflater)
        val sharedPref= requireContext().getSharedPreferences("USER_DETAILS",Context.MODE_PRIVATE)
        binding.textView6.text= sharedPref.getString("name","name")
        binding.textView7.text=sharedPref.getString("gender","MALE")
        binding.textView30.text=sharedPref.getString("age","21")
        binding.textView29.text=sharedPref.getString("weight","64")+" kg"
        binding.textView32.text=sharedPref.getString("bloodgrp","AB+")
        binding.textView25.text=sharedPref.getString("height","164")+" cm"

        return binding.root
    }

}