package com.shaat.zahraa.ui.chats

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shaat.zahraa.data.models.Chat
import com.shaat.zahraa.data.models.User
import com.shaat.zahraa.databinding.ItemChatBinding

class ChatsAdapter(
    private val chats: List<Chat>,
    private val usersMap: Map<String, User>,
    private val onChatClick: (Chat) -> Unit
) : RecyclerView.Adapter<ChatsAdapter.ChatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding = ItemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(chats[position])
    }

    override fun getItemCount() = chats.size

    inner class ChatViewHolder(private val binding: ItemChatBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(chat: Chat) {
            val otherUserId = chat.participants.find { it != com.google.firebase.auth.FirebaseAuth.getInstance().currentUser?.uid }
            val user = otherUserId?.let { usersMap[it] }

            binding.apply {
                textViewChatName.text = user?.displayName ?: "Unknown"
                textViewLastMessage.text = chat.lastMessage
                
                user?.profileImageUrl?.let {
                    Glide.with(binding.root.context)
                        .load(it)
                        .into(imageViewProfile)
                }
                
                root.setOnClickListener {
                    onChatClick(chat)
                }
            }
        }
    }
}
