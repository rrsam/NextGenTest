package com.nextgentest.android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nextgentest.android.models.Country
import com.nextgentest.android.models.Result
import com.nextgentest.android.repo.CountriesRepo
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _countriesFlow : MutableLiveData<Result<List<Country>?>> = MutableLiveData()
    val countriesFlow : LiveData<Result<List<Country>?>> get() = _countriesFlow

    fun getCountries() {
        viewModelScope.launch {
            _countriesFlow.postValue(Result.Loading(true))
            val response = CountriesRepo.getCountriesList()
            _countriesFlow.postValue(Result.Loading(false))
            if(response.isSuccessful){
                _countriesFlow.postValue(Result.Success(response.body()))
            }else{
                _countriesFlow.postValue(Result.Error(response.code(),response.errorBody().toString()))
            }
        }
    }
}