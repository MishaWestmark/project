package com.westmark.chatapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.westmark.chatapp.databinding.ItemRecieveMessageBinding
import com.westmark.chatapp.databinding.ItemSentMessageBinding

class MessageAdapter(val context: Context, val messageList: ArrayList<Message>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val ITEM_RECEIVE = 1
    val ITEM_SENT = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) {
            //inflate receive
            val view =
                LayoutInflater.from(context).inflate(R.layout.item_recieve_message, parent, false)
            return ReceiveViewHolder(view)
        } else {
            //inflate sent
            val view =
                LayoutInflater.from(context).inflate(R.layout.item_sent_message, parent, false)
            return SentViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.javaClass == SentViewHolder::class.java) {
            //do the stuff for sent view holder
            val viewHolder = holder as SentViewHolder
            val currentMessage = messageList[position]
            viewHolder.bind(currentMessage)
        } else {
            //do the stuff for receive view holder
            val viewHolder = holder as ReceiveViewHolder
            val currentMessage = messageList[position]
            viewHolder.bind(currentMessage)

        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]
        if (FirebaseAuth.getInstance().currentUser?.uid == currentMessage.senderId) {
            return ITEM_SENT
        } else {
            return ITEM_RECEIVE
        }
    }

    class SentViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {
        private val binding = ItemSentMessageBinding.bind(view)
        fun bind(message: Message) {
            binding.textViewSentMessage.text = message.message
        }
    }

    class ReceiveViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {
        private val binding = ItemRecieveMessageBinding.bind(view)
        fun bind(message: Message) {
            binding.textViewReceiveMessage.text = message.message
        }
    }


}