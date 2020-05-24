package com.pktstudio.wheresj.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.iid.FirebaseInstanceId
import com.pktstudio.wheresj.R

class MainActivity : AppCompatActivity() {

    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    val e = task.exception
                    Toast.makeText(baseContext, "getInstanceId failed: $e", Toast.LENGTH_SHORT ).show()
                    return@OnCompleteListener
                }
                // Get new Instance ID token
                val token = task.result?.token

                // Log and toast
                val msg = "Teste: o token é: $token"
                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                myRef.child("tokens").setValue(token)

            })



    }
}
