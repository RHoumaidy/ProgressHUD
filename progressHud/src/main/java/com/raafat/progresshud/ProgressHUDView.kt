package com.raafat.progresshud

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.util.AttributeSet
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat

/**
 * Created by Raafat Alhmidi on 7/2/20 @6:51 PM.
 */
class ProgressHUDView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    init {
        val animationDrawable: AnimationDrawable =
            ContextCompat.getDrawable(context, R.drawable.spinner) as AnimationDrawable
        this.setImageDrawable(animationDrawable)
        animationDrawable.start()

    }
}