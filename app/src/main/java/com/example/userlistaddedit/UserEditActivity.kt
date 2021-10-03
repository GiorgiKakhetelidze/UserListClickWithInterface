package com.example.userlistaddedit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.model.User

class UserEditActivity : AppCompatActivity() {

    lateinit var nameEditTxt : EditText
    lateinit var lastNameEditTxt : EditText
    lateinit var mailEditTxt : EditText
    lateinit var saveBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_edit_activity)
        init()
        setListeners()
    }

    private fun init(){
        val data = intent.getParcelableExtra<User>(UsersActivity.USER_KEY)
        nameEditTxt = findViewById(R.id.nameEditTxtView)
        lastNameEditTxt= findViewById(R.id.lastNameEditTxtView)
        mailEditTxt = findViewById(R.id.mailEditTxtView)
        nameEditTxt.setText(data?.name)
        lastNameEditTxt.setText(data?.lastName)
        mailEditTxt.setText(data?.mail)
    }

    private fun setListeners(){
        saveBtn = findViewById(R.id.saveBtnView)
        saveBtn.setOnClickListener{
            saveAndGoToProfile()
        }
    }

    private fun saveAndGoToProfile() {
        val user = User(
            name = nameEditTxt.text.toString(),
            lastName = lastNameEditTxt.text.toString(),
            mail = mailEditTxt.text.toString()
        )
        val resultIntent = Intent()
        resultIntent.putExtra(KEY_EDITED_USER, user)
        setResult(RESULT_OK, resultIntent)
        finish()
    }

    companion object{
        const val KEY_EDITED_USER = "EDITED_USER"
    }

}