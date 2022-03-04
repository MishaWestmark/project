package com.westmark.chatapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.westmark.chatapp.databinding.ActivityChatBinding

class ChatActivity : AppCompatActivity() {
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var messageList: ArrayList<Message>
    private val binding: ActivityChatBinding by viewBinding()
    private lateinit var dbRef: DatabaseReference

    var receiverRoom: String? = null
    var senderRoom: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val name = intent.getStringExtra("name")
        val receiverUid = intent.getStringExtra("uid")
        val senderUid = FirebaseAuth.getInstance().currentUser?.uid

        dbRef =
            FirebaseDatabase.getInstance("https://chatapp-448a1-default-rtdb.asia-southeast1.firebasedatabase.app/").reference

        senderRoom = receiverUid + senderUid
        receiverRoom = senderUid + receiverUid

        supportActionBar?.title = name
        messageList = ArrayList()
        messageAdapter = MessageAdapter(this, messageList)
        with(binding.recyclerViewChat){
            layoutManager = LinearLayoutManager(this@ChatActivity)
            adapter = messageAdapter
        }
        //adding data to recycler view
        dbRef.child("chats").child(senderRoom!!).child("messages")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    messageList.clear()
                    for (postSnapshot in snapshot.children) {
                        val message = postSnapshot.getValue(Message::class.java)
                        messageList.add(message!!)
                    }
                    messageAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })

        //send the message to database
        binding.imageViewSendMessage.setOnClickListener {
            val message = binding.editTextMessage.text.toString()
            val messageObject = Message(message, senderUid)

            dbRef.child("chats").child(senderRoom!!).child("messages").push()
                .setValue(messageObject)
                .addOnSuccessListener {
                    dbRef.child("chats").child(receiverRoom!!).child("messages").push()
                        .setValue(messageObject)
                }
            binding.editTextMessage.setText("")
        }
    }
}