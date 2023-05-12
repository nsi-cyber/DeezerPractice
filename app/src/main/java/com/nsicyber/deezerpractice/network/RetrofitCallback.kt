package com.nsicyber.deezerpractice.network

import android.content.Context
import android.widget.Toast
import com.nsicyber.deezerpractice.PreferencesHelper
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class RetrofitCallback<T> : Callback<T> {
    private var mContext: Context
    private val mCallback: Callback<T>
    private var id: Int

    constructor(context: Context, callback: Callback<T>) {
        mContext = context
        mCallback = callback
        id = 0
    }

    constructor(context: Context, id: Int, callback: Callback<T>) {
        mContext = context
        mCallback = callback
        this.id = id
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
// Do application relavent custom operation like manupulating reponse etc.
        //AlertDialogdismiss(mContext);
        println("-----------BEGIN---------")
        println(" ")
        println("URL      ->" + call.request().url())
        println("METHOD   ->" + call.request().method())
        println("HEADER   ->" + call.request().headers())
        if (call.request().body() != null) {
            println("REQUEST  ->" + bodyToString(call.request().body()))
        } else {
            println("REQUEST  -> null")
        }
        println("RESPONSE ->" + response.body())
        println("Code     ->" + response.code())
        println("Error    ->" + response.errorBody())
        println(" ")
        println("------------END----------")

        if (response.code() == 200) {
            mCallback.onResponse(call, response)
        } else {
            Toast.makeText(
                mContext,
                response.errorBody()
                    .toString() + response.code(),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        println("error")
        println(t.message)
        //Alert Here
        mCallback.onFailure(call, t)
    }


    protected val preferencesHelper: PreferencesHelper?
        protected get() = PreferencesHelper.getInstance(mContext)

    companion object {
        private const val TAG = "CustomRetrofitCallback"
        private fun bodyToString(request: RequestBody?): String {
            return try {
                val buffer = okio.Buffer()
                request!!.writeTo(buffer)
                buffer.readUtf8()
            } catch (e: IOException) {
                "did not work"
            }
        }
    }
}
