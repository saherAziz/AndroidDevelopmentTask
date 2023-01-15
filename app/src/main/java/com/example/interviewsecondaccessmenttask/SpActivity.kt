package com.example.interviewsecondaccessmenttask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.interviewsecondaccessmenttask.databinding.ActivitySpBinding

class SpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.clSplashActivity.alpha = 0f
        binding.clSplashActivity.animate().setDuration(2500).alpha(1f).withEndAction {

            startActivity(Intent(this@SpActivity, HomeActivity::class.java))
            finish()
        }
    }
}