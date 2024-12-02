package com.jhainusa.happypulse

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.jhainusa.happypulse.databinding.ActivityCreateYourAccBinding
import com.jhainusa.happypulse.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val pass = binding.passwordEditText.text.toString()
            if(email.isEmpty()||pass.isEmpty()){
                Toast.makeText(this,"Please fill all the details", Toast.LENGTH_SHORT).show()
            }
            else{
                auth.signInWithEmailAndPassword(email,pass)
                    .addOnCompleteListener{
                        if(it.isSuccessful){
                            Toast.makeText(this,"Sign in successful", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this,MainActivity::class.java))
                        }
                        else{
                            Toast.makeText(this,"Sign in Failed : ${it.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                        finish()
                    }
            }
        }
    }
}