package com.xenia.practice.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.xenia.practice.R
import com.xenia.practice.activity.QRCodeActivity
import com.xenia.practice.model.User

class AdapterUser(private val context: Context,
                  private val listUser: ArrayList<User>):
    RecyclerView.Adapter<AdapterUser.ViewHolderHuman>() {

    class ViewHolderHuman(view: View) : RecyclerView.ViewHolder(view) {
        val textName: TextView = view.findViewById(R.id.tv_name)
        val textEmail: TextView = view.findViewById(R.id.tv_email)
        val imgAvatar : ImageView = view.findViewById(R.id.imgView)
        val cardView: CardView = view.findViewById(R.id.card_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderHuman {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_cell, parent, false)

        return ViewHolderHuman(itemView)
    }

    override fun getItemCount() = listUser.size

    override fun onBindViewHolder(holder: ViewHolderHuman, position: Int) {
        val img = listUser[position].avatar
        val firstName = listUser[position].first_name
        val email = listUser[position].email
        val passId = listUser[position].pass_id

        Picasso.get().load(img).into(holder.imgAvatar)
        holder.textName.text = firstName
        holder.textEmail.text = email

        holder.cardView.setOnClickListener {
            // переходим на карточку с QRCode сотрудника
            val intent = Intent(context, QRCodeActivity::class.java)
            intent.putExtra("pass_id", passId)
            context.startActivity(intent)
        }
    }
}