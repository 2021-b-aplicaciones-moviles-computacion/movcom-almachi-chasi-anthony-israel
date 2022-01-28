package com.example.myapplication

import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result

class IPostHttp (
    val id: Int,
    var userId: Any,
    val title: String,
    var body: String
){
    init {
        if (userId is String){
            userId = (userId as String).toInt()
        }
        if (userId is Int){
            userId = userId
        }
    }
}