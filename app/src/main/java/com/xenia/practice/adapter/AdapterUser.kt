package com.xenia.practice.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
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
import org.json.JSONArray

class AdapterUser(private val context: Context,
                  private val listUser: JSONArray):
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

    override fun getItemCount() = listUser.length()

    override fun onBindViewHolder(holder: ViewHolderHuman, position: Int) {
        val img = listUser.getJSONObject(position).get("avatar").toString()
        val first_name = listUser.getJSONObject(position).get("first_name").toString()
        val email = listUser.getJSONObject(position).get("email").toString()
        val pass_id = listUser.getJSONObject(position).get("pass_id").toString()
        Picasso.get().load(img).into(holder.imgAvatar)
        holder.textName.text = first_name
        holder.textEmail.text = email

        holder.cardView.setOnClickListener {
            val intent = Intent(context, QRCodeActivity::class.java)
            intent.putExtra("pass_id", pass_id)
            context.startActivity(intent)
        }
    }
}