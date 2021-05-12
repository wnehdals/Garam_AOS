package com.jdm.garam.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jdm.garam.R
import com.jdm.garam.base.ViewBindingActivity
import com.jdm.garam.databinding.ActivityMainBinding
import com.jdm.garam.ui.movie.MovieActivity

class MainActivity : ViewBindingActivity<ActivityMainBinding>() {
    override val layoutId = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.movieButton.setOnClickListener {
            var intent = Intent(this, MovieActivity::class.java)
            startActivity(intent)
            Log.e("dfd","dfdf")
        }
    }
}