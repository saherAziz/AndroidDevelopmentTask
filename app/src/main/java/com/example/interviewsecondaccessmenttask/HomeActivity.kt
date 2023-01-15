package com.example.interviewsecondaccessmenttask

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.interviewsecondaccessmenttask.api.FactsService
import com.example.interviewsecondaccessmenttask.api.RetrofitHelper
import com.example.interviewsecondaccessmenttask.databinding.ActivityHomeBinding
import com.example.interviewsecondaccessmenttask.repository.FactsRepository
import com.example.interviewsecondaccessmenttask.viewmodels.FactsViewModelFactory
import com.example.interviewsecondaccessmenttask.viewmodels.MainViewModel


class HomeActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pbLoading.visibility = View.VISIBLE

        getData()
        listeners()
        onBackClick()

    }

     private fun getData() {

         val factsService = RetrofitHelper.getInstance().create(FactsService::class.java)
         val repository = FactsRepository(factsService)
         mainViewModel = ViewModelProvider(this,
             FactsViewModelFactory(repository)
             )[MainViewModel::class.java]

         mainViewModel.facts.observe(this) {
             binding.pbLoading.visibility = View.GONE
             binding.tvFacts.text = it.fact
             binding.btnRefresh.visibility = View.VISIBLE
         }

         mainViewModel.getFacts()
     }


    private fun listeners() {

        binding.btnRefresh.setOnClickListener {
            binding.tvFacts.text = ""
            binding.pbLoading.visibility = View.VISIBLE
            binding.btnRefresh.visibility = View.GONE

            mainViewModel.facts.removeObservers(this)
            getData()

        }
    }


    private fun onBackClick() {
        if (Build.VERSION.SDK_INT >= 33) {
            onBackInvokedDispatcher.registerOnBackInvokedCallback(
                OnBackInvokedDispatcher.PRIORITY_DEFAULT
            ) {

                exitOnBackPressed()
            }
        } else {
            onBackPressedDispatcher.addCallback(
                this,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {

                        Log.i("TAG", "handleOnBackPressed: Exit")
                        exitOnBackPressed()
                    }
                })
        }
    }

    private fun exitOnBackPressed() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Are you sure You Want to Logout?")

        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }

        builder.setPositiveButton("yes") { _, _ ->

            finish()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}