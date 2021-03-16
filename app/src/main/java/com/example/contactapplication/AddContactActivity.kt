package com.example.contactapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.FirebaseFirestore


class AddContactActivity : AppCompatActivity() {
    lateinit var db: FirebaseFirestore
    lateinit var btn_save:Button
    lateinit var et_name:EditText
    lateinit var et_number:EditText
    lateinit var et_address:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        getEelements()

        btn_save.setOnClickListener {
            addPersonToDB(et_name.text.toString(),et_number.text.toString(),et_address.text.toString())
        }
    }
    private fun addPersonToDB(name: String, number: String, address: String) {
        val newPerson = hashMapOf("name" to name, "number" to number, "address" to address)
            db.collection("contacts")
                .add(newPerson)
                .addOnSuccessListener { documentReference ->
                    Log.e("TAG", "User added Successfully with user id ${documentReference.id}")
                }
                .addOnFailureListener { exception ->
                    Log.e("TAG", exception.message.toString())
                }
        }
    private fun getEelements(){
        db = FirebaseFirestore.getInstance()
        btn_save =findViewById(R.id.btn_save)
        et_name =findViewById(R.id.et_name)
        et_number =findViewById(R.id.et_number)
        et_address =findViewById(R.id.et_address)

    }

}
