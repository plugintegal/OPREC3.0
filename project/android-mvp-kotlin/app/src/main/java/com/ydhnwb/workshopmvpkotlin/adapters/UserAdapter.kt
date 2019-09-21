package com.ydhnwb.workshopmvpkotlin.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ydhnwb.workshopmvpkotlin.DetailActivity
import com.ydhnwb.workshopmvpkotlin.R
import com.ydhnwb.workshopmvpkotlin.models.User
import kotlinx.android.synthetic.main.list_item_user.view.*

class UserAdapter(var users : List<User>, var context: Context) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_user, parent, false))


    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(context, users[position])

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(context: Context, user : User){
            itemView.user_fullname.text = user.name
            itemView.user_email.text = user.email
            itemView.setOnClickListener {
                context.startActivity(Intent(context, DetailActivity::class.java).apply {
                    putExtra("ID", user.id)
                })
            }
        }
    }
}