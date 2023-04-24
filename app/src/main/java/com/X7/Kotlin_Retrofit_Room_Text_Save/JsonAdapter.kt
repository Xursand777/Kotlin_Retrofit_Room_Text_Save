package com.X7.Kotlin_Retrofit_Room_Text_Save

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.X7.Kotlin_Retrofit_Room_Text_Save.databinding.RecyclerviewItemBinding
import com.X7.Kotlin_Retrofit_Room_Text_Save.retrofit.JsonModel


class JsonAdapter constructor(
    val context: Context,
    val arrayList: ArrayList<JsonModel>
):RecyclerView.Adapter<JsonAdapter.JsonViewHolder>(){

    class JsonViewHolder(val binding: RecyclerviewItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JsonViewHolder {
        val view=RecyclerviewItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return JsonViewHolder(view)
    }

    override fun getItemCount(): Int =arrayList.size

    override fun onBindViewHolder(holder: JsonViewHolder, position: Int) {
        holder.binding.textview1.text="${arrayList.get(position).title}\n ${arrayList.get(position).body}\n ${arrayList.get(position).id} - ${arrayList.get(position).userId} "

    }
}