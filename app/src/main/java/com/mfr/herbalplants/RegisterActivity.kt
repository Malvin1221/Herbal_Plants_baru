package com.mfr.herbalplants

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.mfr.herbalplants.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnRegister.setOnClickListener{
            val email = binding.edtEmailRegister.text.toString()
            val password = binding.edtPasswordRegister.text.toString()

            //Validasi Email
            if(email.isEmpty()){
                binding.edtEmailRegister.error = "Email Harus Diisi !!!"
                binding.edtEmailRegister.requestFocus()
                return@setOnClickListener
            }
            //Validasi Email Apakah Sesuai
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.edtEmailRegister.error = "Email Harus Diisi !!!"
                binding.edtEmailRegister.requestFocus()
                return@setOnClickListener
            }
            //Validasi Password
            if(password.isEmpty()){
                binding.edtPasswordRegister.error = "Password Harus Diisi !!!"
                binding.edtPasswordRegister.requestFocus()
                return@setOnClickListener
            }
            //Validasi Panjang Password
            if (password.length < 6){
                binding.edtPasswordRegister.error = "Password Minimal 6 Karakter !!!"
                binding.edtPasswordRegister.requestFocus()
                return@setOnClickListener
            }
            RegisterFireBase(email, password)
        }
    }
    private fun RegisterFireBase(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    Toast.makeText(this,"Berhasil Terdaftar", Toast.LENGTH_LONG).show()
                    val intent = Intent(this,LoginActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this,"${it.exception?.message}",Toast.LENGTH_LONG).show()
                }
            }
    }
}