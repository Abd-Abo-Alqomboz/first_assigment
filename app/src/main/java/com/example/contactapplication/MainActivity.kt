package com.example.contactapplication

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    lateinit var recycle:RecyclerView
    lateinit var db: FirebaseFirestore
    lateinit var adapter: PersonAdapter
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = FirebaseFirestore.getInstance()
        showProgress()


        recycle = findViewById(R.id.recycle)

        recycle.layoutManager = LinearLayoutManager(this)
        //rvStudents.layoutManager = GridLayoutManager(this,2)

        recycle.setHasFixedSize(true)

        adapter = PersonAdapter(this, person)
        recycle.adapter = adapter

    }
    private fun getAllUsers(){

        db.collection("contacts")
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot) {
                    val name = document.getString("name").toString()
                    val number = document.getString("number").toString()
                    val address = document.getString("address").toString()

                    person.add(Person(name, number, address))

                    Log.e("TAG", "${document.getString("name").toString()} ")
                    Log.e("TAG", "${document.id} => ${document.getString("name")}")
                }
                adapter.notifyDataSetChanged()
                progressDialog.dismiss()
            }
            .addOnFailureListener { exception ->
                Log.e("TAG", exception.message.toString())
            }
    }

    companion object{
        val person = ArrayList<Person>()
    }
    private fun showProgress(){
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("loading....")
        progressDialog.setCancelable(false)
        progressDialog.show()
    }
}
