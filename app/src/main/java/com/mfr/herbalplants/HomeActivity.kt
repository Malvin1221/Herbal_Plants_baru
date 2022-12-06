package com.mfr.herbalplants

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mfr.herbalplants.viewTanaman.AdminTanamanActivity
import com.mfr.herbalplants.viewTanaman.TanamanActivity
import kotlinx.android.synthetic.main.activity_admin_tanaman.*
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        imgvToTanaman.setOnClickListener{
            startActivity(Intent(this,TanamanActivity::class.java))
        }
        imgvToMinuman.setOnClickListener {
            startActivity(Intent(this,MinumanActivity::class.java))
        }
        txtDeveloper.setOnClickListener{
            startActivity(Intent(this,LoginActivityAdmin::class.java))
        }
    }
}