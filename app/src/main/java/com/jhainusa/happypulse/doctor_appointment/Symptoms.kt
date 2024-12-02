package com.jhainusa.happypulse.doctor_appointment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.jhainusa.happypulse.DiseaseOutput
import com.jhainusa.happypulse.R
import com.jhainusa.happypulse.databinding.ActivityMainBinding
import com.jhainusa.happypulse.databinding.FragmentSymtomsBinding


class Symptoms : Fragment() {
    private lateinit var binding: FragmentSymtomsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentSymtomsBinding.inflate(layoutInflater)
        val diseases = ArrayList<String>()
        val buttons = listOf<AppCompatButton>(
            binding.StomachPain,
            binding.ChestPain,
            binding.Cough,
            binding.UclersoFtongue,
            binding.Vomiting,
            binding.LossOfAppetite,
            binding.YellowingOfEyes,
            binding.FamilyGenetics,
            binding.fatigue,
            binding.YellowishSkin
        )

        buttons.forEach { button ->
            var isDefaultBackground = true // Track state for each button

            button.setOnClickListener {
                if (isDefaultBackground) {
                    button.setBackgroundResource(R.color.greenbutton)
                    diseases.add(button.text.toString())
                } else {
                    button.setBackgroundResource(R.color.symptomButtonColor)
                    diseases.removeAt(diseases.indexOf(button.text.toString()))
                }
                isDefaultBackground = !isDefaultBackground
                Log.d("disesases",diseases.toString())
            }
        }
        binding.btnNext.setOnClickListener {
              startActivity(Intent(requireContext(),DiseaseOutput::class.java))
        }
        return binding.root
    }

}