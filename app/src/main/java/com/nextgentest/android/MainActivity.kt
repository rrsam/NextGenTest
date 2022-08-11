package com.nextgentest.android

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.nextgentest.android.adapters.CountryAdapter
import com.nextgentest.android.databinding.ActivityMainBinding
import com.nextgentest.android.models.Result
import com.nextgentest.android.utils.ItemDecor
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val countryAdapter by lazy {
        CountryAdapter()
    }

    private val mainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvCountryList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = countryAdapter
            addItemDecoration(ItemDecor(this@MainActivity))
        }

        mainViewModel.getCountries()

        lifecycleScope.launch {
            mainViewModel.countriesFlow.observe(this@MainActivity) {
                when (it) {
                    is Result.Success -> { countryAdapter.submitList(it.data)}
                    is Result.Error -> { showAlert(it.errorCode,it.errorMessage)}
                    is Result.Loading -> {
                        if(it.status) binding.loading.visibility= View.VISIBLE else binding.loading.visibility = View.GONE
                    }
                }
            }
        }


    }

    private fun showAlert(errorCode: Int, errorMessage: String) {
        AlertDialog.Builder(this)
            .setTitle("Error : $errorCode")
            .setMessage(errorMessage)
            .setPositiveButton("Retry"){ dialogInterface , _ ->
                dialogInterface.dismiss()
                mainViewModel.getCountries()
            }
            .setNegativeButton("Cancel"){ dialogInterface ,_ ->
                dialogInterface.dismiss()
            }
            .create().show()
    }
}