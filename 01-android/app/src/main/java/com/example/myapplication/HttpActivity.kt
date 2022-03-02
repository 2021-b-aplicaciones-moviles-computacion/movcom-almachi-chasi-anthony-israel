package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result

class HttpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http)
        metodoGet()
        metodoPost()
    }

    fun metodoGet(){
        val identificadorPost = 1
        val url = "https://jsonplaceholder.typicode.com/posts/" + identificadorPost.toString()
        url
            .httpGet()
            .responseString { req, res, result ->
                when (result) {
                    // import com.github.kittinunf.result.Result
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("http-klaxon", "Error: ${error}")
                    }
                    is Result.Success -> {
                        val respuestaString = result.get()
                        Log.i("http-klaxon", "${respuestaString}")
                        // "https://jsonplaceholder.typicode.com/posts/1" 1
                        // "https://jsonplaceholder.typicode.com/posts" muchos
                        val post = Klaxon()
                            .parse<IPostHttp>(respuestaString)
                        // .parseArray<IPostHttp>(getString)
                        Log.i("http-klaxon", "${post?.body}")
                    }
                }
            }
    }

    fun metodoPost(){
        val parametros: List<Pair<String, *>> = listOf(
            "title" to "Titulo moviles",
            "body" to "descripcion moviles",
            "userId" to 1
        )
        val url = "https://jsonplaceholder.typicode.com/posts"
        url
            .httpPost(parametros)
            .responseString { req, res, result ->
                when (result) {
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("http-klaxon", "Error: ${error}")
                    }
                    is Result.Success -> {
                        val postString = result.get()
                        Log.i("http-klaxon", "${postString}")
                        val post = Klaxon()
                            .parse<IPostHttp>(postString)
                        Log.i("http-klaxon", "${post?.title}")
                    }
                }
            }
    }

}