package com.example.userlistaddedit

import com.example.model.User

interface OnEditClickListener {
    fun onItemClick(user : User, position :Int)
}