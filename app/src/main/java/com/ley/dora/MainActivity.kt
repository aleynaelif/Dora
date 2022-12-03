package com.ley.dora

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.ActionCodeEmailInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ley.dora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth

        //if there is a current user already signed in then keep them signed in.
        val currentUser = auth.currentUser

        if(currentUser != null){
            val intent = Intent(this,HomePageActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun KayitOl(view : View){
        val email = binding.EmailText.text.toString()
        val password = binding.PasswordText.text.toString()

        if(email.isNotEmpty() && password.isNotEmpty()){
            auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
                //success
                val intent = Intent(this,HomePageActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this@MainActivity,it.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }
        else{
            Toast.makeText(this@MainActivity,"Email ve şifre giriniz!", Toast.LENGTH_LONG).show()
        }
    }

    fun GirisYap(view : View){
        val email = binding.EmailText.text.toString()
        val password = binding.PasswordText.text.toString()

        if(email.equals("") || password.equals("")){
            Toast.makeText(this,"Email ve şifre giriniz!",Toast.LENGTH_LONG).show()
        }else{
            auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
                val intent = Intent(this@MainActivity,HomePageActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this@MainActivity,it.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }
    }
}