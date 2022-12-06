package com.mfr.herbalplants.viewTanaman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.mfr.herbalplants.R
import com.mfr.herbalplants.adapter.ListAdapter
import com.mfr.herbalplants.model.Tanaman
import kotlinx.android.synthetic.main.activity_tanaman.*

class TanamanActivity : AppCompatActivity() {

    private var mStorage:FirebaseStorage? = null
    private var mDatabaseRef:DatabaseReference? = null
    private var mDBListener:ValueEventListener? = null
    private lateinit var mTanaman:MutableList<Tanaman>
    private lateinit var listAdapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tanaman)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@TanamanActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mTanaman = ArrayList()
        listAdapter = ListAdapter(this@TanamanActivity,mTanaman)
        mRecyclerView.adapter = listAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("tanaman_upload")
        mDBListener = mDatabaseRef!!.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@TanamanActivity,error.message, Toast.LENGTH_LONG).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
              }
            override fun onDataChange(snapshot: DataSnapshot) {
                mTanaman.clear()
                for (tanamanSnapshot in snapshot.children){
                    val upload = tanamanSnapshot.getValue(Tanaman::class.java)
                    upload!!.key = tanamanSnapshot.key
                    mTanaman.add(upload)
                }
                listAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}