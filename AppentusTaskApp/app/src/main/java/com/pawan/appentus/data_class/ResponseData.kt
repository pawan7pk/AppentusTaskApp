package com.pawan.appentus.data_class

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_author.view.*

class ResponseData : ArrayList<ResponseData.ResponseDataItem>() {
    data class ResponseDataItem(
        val author: String,
        val download_url: String,
        val height: Int,
        val id: String,
        val url: String,
        val width: Int
    ) {
        companion object {
            @BindingAdapter("profileImage")
            @JvmStatic
            fun loadImage(view: ImageView, url: String) {
                Glide.with(view.context)
                    .load(url)
                    .apply(
                        RequestOptions()
                    ).into(view.iv_author)

            }
        }
    }
}