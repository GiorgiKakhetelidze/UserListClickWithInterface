package com.example.userlistaddedit

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.model.User

class UsersActivity : AppCompatActivity() {

    private var position = -1

    private lateinit var recyclerView: RecyclerView

    private val adapter = UserAdapter(object  : OnEditClickListener{
        override fun onItemClick(user: User, position: Int) {
            this@UsersActivity.position = position
            navigateToDetailsScreenForResult(user)
        }
    })

/*    this@UsersActivity.position = position
    navigateToDetailsScreenForResult(user)*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        setRecycler()
    }

    private fun navigateToDetailsScreenForResult(user: User) {
        val intent = Intent(this, UserEditActivity::class.java).apply {
            putExtra(USER_KEY, user)
        }
        resultLauncher.launch(intent)
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val user = result.data?.getParcelableExtra<User>(UserEditActivity.KEY_EDITED_USER)
                setDataFromResult(user)
            }
        }

    private fun setDataFromResult(user: User?) {
        adapter.list[position] = user!!
        adapter.notifyItemChanged(position)
    }

    private fun setRecycler() {
        recyclerView = findViewById(R.id.recycleView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
        adapter.list = mutableListOf(
            User("Gio", "Kakhetelidze", "mail"),
            User("Gio", "Kakhetelidze", "mail"),
            User("Gio", "Kakhetelidze", "mail"),
            User("Gio", "Kakhetelidze", "mail"),
            User("Gio", "Kakhetelidze", "mail"),
            User("Gio", "Kakhetelidze", "mail"),
            User("Gio", "Kakhetelidze", "mail"),
            User("Gio", "Kakhetelidze", "mail"),
            User("Gio", "Kakhetelidze", "mail"),
            User("Gio", "Kakhetelidze", "mail"),
            User("Gio", "Kakhetelidze", "mail"),
            User("Gio", "Kakhetelidze", "mail")
        )
    }

    companion object {
        const val USER_KEY = "USER"
    }
}