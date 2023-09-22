package com.example.valorantapplicationwithmvvm.glideModule

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.example.valorantapplicationwithmvvm.R

class GlideModule() : AppGlideModule() {
        companion object {
            fun setImage(imageView: ImageView, context: Context, url: String?) {
                val requestOptions = RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)
                    .centerCrop()
                    .error(R.drawable.ic_launcher_background)

                Glide.with(context)
                    .applyDefaultRequestOptions(requestOptions)
                    .load(url)
                    .into(imageView)
            }

        }
    }