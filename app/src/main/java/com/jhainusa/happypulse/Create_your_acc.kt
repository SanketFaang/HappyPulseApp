package com.jhainusa.happypulse

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.jhainusa.happypulse.databinding.ActivityCreateYourAccBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class Create_your_acc : AppCompatActivity() {
    private lateinit var binding: ActivityCreateYourAccBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityCreateYourAccBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth=FirebaseAuth.getInstance()
        binding.signUpButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val pass = binding.passwordEditText.text.toString()
            if(email.isEmpty()||pass.isEmpty()){
                Toast.makeText(this,"Please fill all the details",Toast.LENGTH_SHORT).show()
            }
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    auth.createUserWithEmailAndPassword(email, pass).await()
                    Toast.makeText(this@Create_your_acc,"Registration Successful",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@Create_your_acc,MakeYourProfile::class.java))
                    finish()
                }
                catch (e:Exception){
                    Toast.makeText(this@Create_your_acc,"Registration failed : ${e.message}",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
