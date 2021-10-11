package com.example.bedpatient

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import java.net.Inet4Address

class Register : AppCompatActivity() { //ประกาศตัวแปร
    lateinit var txtEmailCreate:EditText
    lateinit var txtPasswordCreate:EditText
    lateinit var txtFullName:EditText
    lateinit var buttonSubmit:Button
    lateinit var gender1:CheckBox
    lateinit var gender2:CheckBox
    lateinit var txtCGDT:EditText
    lateinit var txtAddress:EditText
    lateinit var txtPhone:EditText


    lateinit var email:String
    lateinit var password:String

    lateinit var mAuth: FirebaseAuth
    lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        txtEmailCreate = findViewById<EditText>(R.id.txtEmailCreate)
        txtPasswordCreate = findViewById<EditText>(R.id.txtPasswordCreate)
        buttonSubmit = findViewById<Button>(R.id.buttonSubmit)
        txtFullName = findViewById<EditText>(R.id.txtFullName)
        gender1 = findViewById<CheckBox>(R.id.gender1)
        gender2 = findViewById<CheckBox>(R.id.gender2)
        txtCGDT = findViewById<EditText>(R.id.txtCGDT)
        txtAddress = findViewById<EditText>(R.id.txtAddress)
        txtPhone = findViewById<EditText>(R.id.txtPhone)

        //ส่วนของ Firebase
        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        buttonSubmit.setOnClickListener {
            creatAccount()
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser
        updateUI(currentUser)
    }

    private fun creatAccount() {
        email = txtEmailCreate.text.toString()
        password = txtPasswordCreate.text.toString()
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this) { task -> if (task.isSuccessful){
            Log.d("MyApp","Create New User Success!")
                val user = mAuth.currentUser
                val databaseReference = database.reference.child("users").push()
                databaseReference.child("uid").setValue(user!!.uid)
                databaseReference.child("email").setValue(user.email)
                databaseReference.child("fullname").setValue(txtFullName.text.toString())
                databaseReference.child("gender").setValue(gender1.text.toString())
                databaseReference.child("gender").setValue(gender2.text.toString())
                databaseReference.child("Congidital").setValue(txtCGDT.text.toString())
                databaseReference.child("Address").setValue(txtAddress.text.toString())
                databaseReference.child("Tel").setValue(txtPhone.text.toString())
            updateUI(user)
            } else{
                Log.w("MyApp","Failure Process!",task.exception)
                Toast.makeText(this@Register,"Authentication Failed.",Toast.LENGTH_SHORT).show()
                updateUI(null)
            }
        }

    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            val uid = user.uid
            val email = user.email
            Toast.makeText(
                this@Register,
                "Welcome: $email your ID is : $uid",
                Toast.LENGTH_SHORT
            ).show()
            val intentSession = Intent(this, MenuActivity::class.java)
            startActivity(intentSession)
        }
    }
}