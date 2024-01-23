package com.example.dashboard.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapters {
    @BindingAdapter("checkText")
    @JvmStatic
    fun TextView.checkAndSetText(txt:String?){
        if (!txt.isNullOrEmpty() && txt!="null"){
            text=txt
        }else{
            visibility= View.GONE
        }
    }
    @BindingAdapter("firstText","secondText")
    @JvmStatic
    fun TextView.checkAndSetText(first:String?, second:String?){
        if (!first.isNullOrEmpty() && first!="null" &&  !second.isNullOrEmpty() && second!="null"){
            text=first.plus(" - ").plus(second)
        }else{
            visibility= View.GONE
        }
    }
    @BindingAdapter(value = ["imageUrl", "placeholder"], requireAll = false)
    @JvmStatic
    fun ImageView.loadImage(url: String?, error: Int?) {
        if (!url.isNullOrEmpty() && url!="null") Glide.with(context)
            .load(url)
            .error(error)
            .into(this)
    }
}