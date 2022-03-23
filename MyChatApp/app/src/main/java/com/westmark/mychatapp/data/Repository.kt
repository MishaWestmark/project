package com.westmark.mychatapp.data

import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Repository {
    private val mAuth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance()
    private val userRef = database.reference


    fun login(email: String, password: String): Task<AuthResult> {
        return mAuth.signInWithEmailAndPassword(email, password)
    }


    fun signUp(name: String, email: String, password: String): Task<AuthResult> {
        return mAuth.createUserWithEmailAndPassword(email, password)
    }

    fun getUsers(liveData: MutableLiveData<List<User>>) {
        userRef.child("user").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userList = snapshot.children.map {
                    it.getValue(User::class.java)!!
                }
                liveData.postValue(userList)
            }

            override fun onCancelled(error: DatabaseError) {}
        })
}

fun addUserToDatabase(name: String, email: String) {
    val uid = mAuth.currentUser?.uid.toString()
    userRef.child("user").child(uid).setValue(User(uid, name, email))
}
}
