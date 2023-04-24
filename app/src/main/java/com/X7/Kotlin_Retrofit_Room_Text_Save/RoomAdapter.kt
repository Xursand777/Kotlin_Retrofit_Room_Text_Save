package com.X7.Kotlin_Retrofit_Room_Text_Save

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.X7.Kotlin_Retrofit_Room_Text_Save.databinding.RecyclerviewItemBinding
import com.X7.Kotlin_Retrofit_Room_Text_Save.room.UserModel


class RoomAdapter constructor(
    val context: Context,
    val arrayList: ArrayList<UserModel>
):RecyclerView.Adapter<RoomAdapter.RoomViewHolder>(){

    class RoomViewHolder(val binding: RecyclerviewItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view=RecyclerviewItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return RoomViewHolder(view)
    }

    override fun getItemCount(): Int =arrayList.size

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        holder.binding.textview1.text="${arrayList.get(position).title}\n ${arrayList.get(position).body}"

    }
}