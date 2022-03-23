package com.westmark.mychatapp.ui.userList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.westmark.mychatapp.R
import com.westmark.mychatapp.data.User
import com.westmark.mychatapp.databinding.UserListFragmentBinding
import com.westmark.mychatapp.utils.autoCleared
import timber.log.Timber

class UserListFragment : Fragment(R.layout.user_list_fragment) {
    private val binding: UserListFragmentBinding by viewBinding()
    private var userAdapter: UserListAdapter by autoCleared()
    private val viewModel: UserListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        bindViewModel()
    }

    private fun initList(){
        userAdapter = UserListAdapter()
        with(binding.recyclerViewUsers){
            adapter = userAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
    private fun bindViewModel(){
        viewModel.getUsersList()
        viewModel.userLiveData.observe(viewLifecycleOwner){
            userAdapter.updateAdapter(it)
            Timber.d("list $it")
            Log.d("bindViewModel","$it")
        }
    }

}