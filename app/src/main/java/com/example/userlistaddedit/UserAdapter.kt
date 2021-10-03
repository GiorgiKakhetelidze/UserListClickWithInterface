package com.example.userlistaddedit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.model.User

class UserAdapter(private val clickListener: OnEditClickListener) :
    RecyclerView.Adapter<UserAdapter.ItemViewHolder>() {

    var list: MutableList<User> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind()
        holder.setListeners()
    }

    override fun getItemCount() = list.size

    inner class ItemViewHolder(
        private val view: View
    ) : RecyclerView.ViewHolder(view) {

        lateinit var curData: User
        private val name: TextView = view.findViewById(R.id.nameTxtView)
        private val lastName: TextView = view.findViewById(R.id.lastNameTxtView)
        private val mail: TextView = view.findViewById(R.id.mailTxtView)

        fun bind() {
            curData = list[adapterPosition]
            name.text = curData.name
            lastName.text = curData.lastName
            mail.text = curData.mail
        }

        fun setListeners() {
            val deleteBtn: ImageButton = view.findViewById(R.id.btnDeleteImgView)
            deleteBtn.setOnClickListener {
                list.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
            }

            val updateBtn: ImageButton = view.findViewById(R.id.btnEditImgView)
            updateBtn.setOnClickListener {
                val user = list[adapterPosition]
                clickListener.onItemClick(user = user, position = adapterPosition)
            }
        }

    }
}