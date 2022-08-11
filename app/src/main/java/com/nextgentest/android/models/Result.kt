package com.nextgentest.android.models


sealed class Result<T> {
    class Success<T>(val data: T) : Result<T>()
    class Loading<T>(val status : Boolean) : Result<T>()
    class Error<T>(val errorCode : Int,val errorMessage: String) : Result<T>()
}
