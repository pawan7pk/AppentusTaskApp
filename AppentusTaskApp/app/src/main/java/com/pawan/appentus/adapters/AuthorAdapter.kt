package com.pawan.appentus.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pawan.appentus.R
import com.pawan.appentus.data_class.ResponseData
import com.pawan.appentus.databinding.ItemAuthorBinding

class AuthorAdapter : RecyclerView.Adapter<AuthorAdapter.AuthorViewHolder>() {

    private var authorList: ResponseData? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorViewHolder {
        val binding: ItemAuthorBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_author,
            parent,
            false
        )
        return AuthorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AuthorViewHolder, position: Int) {
        holder.itemAuthorBinding.author = authorList?.get(position)
    }
    override fun getItemCount(): Int {
        return authorList?.size ?: 0
    }

    fun setAuthorList(list: ResponseData?){
        authorList = list
        notifyDataSetChanged()
    }

    class AuthorViewHolder(val itemAuthorBinding: ItemAuthorBinding) :
        RecyclerView.ViewHolder(itemAuthorBinding.root)


}