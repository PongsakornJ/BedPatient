package com.example.bedpatient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*


class Arcticles : AppCompatActivity() {

    lateinit var recyclerViewArcticle : RecyclerView
    lateinit var mAuth: FirebaseAuth
    lateinit var database: FirebaseDatabase
    lateinit var databaseReferenceArcticle: DatabaseReference
    lateinit var responseArcticle: MutableList<ArcticleModel>
    private var arcticleAdapter: ArcticleAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arcticles)

        recyclerViewArcticle = findViewById<RecyclerView>(R.id.recyclerViewArcticle)

        //ส่วนของ Firebase
        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        recyclerViewArcticle.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        databaseReferenceArcticle = database.getReference("Arcticle/")
        responseArcticle = mutableListOf()
        arcticleAdapter = ArcticleAdapter(responseArcticle as ArrayList<ArcticleModel>)
        recyclerViewArcticle.adapter = arcticleAdapter
        onBindingFirebase()

    }

    private fun onBindingFirebase() {
        databaseReferenceArcticle.addChildEventListener(object: ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                responseArcticle.add(snapshot.getValue(ArcticleModel::class.java)!!)
                arcticleAdapter!!.notifyDataSetChanged()
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildRemoved(snapshot: DataSnapshot) {

            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            val uid = user.uid
            val email = user.email
            Toast.makeText(
                this@Arcticles,
                "Welcome: $email your ID is : $uid",
                Toast.LENGTH_SHORT
            ).show()
        }else{
            val intentSession = Intent(this, MainActivity::class.java)
            startActivity(intentSession)
        }
    }
}