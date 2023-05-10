package com.nsicyber.deezerpractice.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Parser {

    companion object {

        inline fun <reified T> parse(src: Any?):T? {
            src?.let {
                return Gson().
                fromJson<T>(
                    Gson().toJson(src), object: TypeToken<T>() {}.type)
            }
            return null
        }

    }

}