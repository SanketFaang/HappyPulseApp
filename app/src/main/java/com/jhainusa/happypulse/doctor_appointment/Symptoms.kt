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
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.jhainusa.happypulse.DiseaseOutput
import com.jhainusa.happypulse.R
import com.jhainusa.happypulse.databinding.ActivityMainBinding
import com.jhainusa.happypulse.databinding.FragmentSymtomsBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okio.IOException
import org.json.JSONObject


class Symptoms : Fragment() {
    private lateinit var binding: FragmentSymtomsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun predictDisease(symptoms: List<String>, onResult: (String) -> Unit) {
        val client = OkHttpClient()
        val mediaType = "application/json".toMediaTypeOrNull()

        // Create the JSON payload with comma-separated symptoms
        val body = RequestBody.create(mediaType, JSONObject().apply {
            put("symptoms", symptoms.joinToString(", ")) // Join symptoms with commas
        }.toString())

        // Build the API request
        val request = Request.Builder()
            .url("https://human-disease-detector.p.rapidapi.com/human_disease/predict")
            .post(body)
            .addHeader("x-rapidapi-key", "a54eae785cmsh1349638218b8039p17e9f0jsn9618365053bd")
            .addHeader("x-rapidapi-host", "human-disease-detector.p.rapidapi.com")
            .addHeader("Content-Type", "application/json")
            .addHeader("x-token", "Makshad Nai Bhoolna @ 2025")
            .build()

        // Make the HTTP call
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onResult("Failed: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()
                if (!response.isSuccessful) {
                    Log.e("API_ERROR", "Code: ${response.code}, Body: $responseBody")
                    onResult("Error ${response.code}: $responseBody")
                } else {
                    onResult(responseBody ?: "No response data")
                }
            }
        })
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
            binding.YellowishSkin,
            binding.acidity,
            binding.highFever,
            binding.dehydration,
            binding.indigestion,
            binding.diarrhoea,
            binding.dizziness
        )

        buttons.forEach { button ->
            var isDefaultBackground = true // Track state for each button

            button.setOnClickListener {
                if (isDefaultBackground) {
                    button.setBackgroundResource(R.drawable.symbutton)
                    diseases.add(button.text.toString())
                } else {
                    button.setBackgroundResource(R.drawable.button_background)
                    diseases.removeAt(diseases.indexOf(button.text.toString()))
                }
                isDefaultBackground = !isDefaultBackground
            }
        }
        binding.btnNext.setOnClickListener {
            if (diseases.isNotEmpty()) {
                predictDisease(diseases) { result ->
                    activity?.runOnUiThread {
                        val intent = Intent(requireContext(), DiseaseOutput::class.java)
                        intent.putExtra("result", result)
                        startActivity(intent)
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Please select at least one symptom", Toast.LENGTH_SHORT).show()
            }
        }


        return binding.root
    }

}