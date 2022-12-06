package com.mfr.herbalplants

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.mfr.herbalplants.viewTanaman.AdminTanamanActivity

class MainActivity : AppCompatActivity() {

    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnTanaman = findViewById<Button>(R.id.btn_Tanaman)
        btnTanaman.setOnClickListener{
            val x = Intent(this, AdminTanamanActivity::class.java)
            startActivity(x)
        }
        val btnMinuman = findViewById<Button>(R.id.btn_Minuman)
        btnMinuman.setOnClickListener{
            val y = Intent(this, AdminMinumanActivity::class.java)
            startActivity(y)
        }
    }
}