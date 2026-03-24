package com.shaat.zahraa.ui.chats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.shaat.zahraa.data.models.Chat
import com.shaat.zahraa.data.models.User
import com.shaat.zahraa.databinding.FragmentChatsBinding

class ChatsFragment : Fragment() {

    private var _binding: FragmentChatsBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var chatsAdapter: ChatsAdapter
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    private val chatsList = mutableListOf<Chat>()
    private val usersMap = mutableMapOf<String, User>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatsBinding.inflate(inflater, container, false)
        
        initializeFirebase()
        setupRecyclerView()
        loadChats()
        
        binding.fabNewChat.setOnClickListener {
            // Navigate to new chat
        }
        
        return binding.root
    }
    
    private fun initializeFirebase() {
        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()
    }
    
    private fun setupRecyclerView() {
        chatsAdapter = ChatsAdapter(chatsList, usersMap) { chat ->
            // Navigate to chat activity
            val intent = android.content.Intent(requireContext(), com.shaat.zahraa.ui.chat.ChatActivity::class.java)
            intent.putExtra("chatId", chat.chatId)
            startActivity(intent)
        }
        
        binding.recyclerViewChats.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = chatsAdapter
        }
    }
    
    private fun loadChats() {
        val currentUserId = auth.currentUser?.uid ?: return
        
        database.reference
            .child("chats")
            .orderByChild("participants/$currentUserId")
            .equalTo(true)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    chatsList.clear()
                    
                    for (chatSnapshot in snapshot.children) {
                        val chat = chatSnapshot.getValue(Chat::class.java)
                        chat?.let {
                            chatsList.add(it)
                            loadUserInfo(it)
                        }
                    }
                    
                    chatsList.sortByDescending { it.lastMessageTime }
                    chatsAdapter.notifyDataSetChanged()
                }
                
                override fun onCancelled(error: DatabaseError) {
                    // Handle error
                }
            })
    }
    
    private fun loadUserInfo(chat: Chat) {
        val otherUserId = chat.participants.find { it != auth.currentUser?.uid } ?: return
        
        if (usersMap.containsKey(otherUserId)) return
        
        database.reference
            .child("users")
            .child(otherUserId)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = snapshot.getValue(User::class.java)
                    user?.let {
                        usersMap[otherUserId] = it
                        chatsAdapter.notifyDataSetChanged()
                    }
                }
                
                override fun onCancelled(error: DatabaseError) {}
            })
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
