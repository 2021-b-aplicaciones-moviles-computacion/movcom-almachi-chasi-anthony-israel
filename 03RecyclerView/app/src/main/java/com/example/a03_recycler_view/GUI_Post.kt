package com.example.a03_recycler_view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.google.android.material.textfield.TextInputEditText

class GUI_Post : AppCompatActivity() {

    var active_user = BPersona("", 0, "", "", "", "", "")
    var post = BPost("",1,"","","","","","","","")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gui_post)
    }

    override fun onStart() {
        super.onStart()

        active_user = intent.getParcelableExtra<BPersona>("active-user")!!

        var imageView_profilePic = findViewById<ImageView>(R.id.iv_post_user_profile_pic)
        imageView_profilePic.setImageResource(active_user.profilePic)

        var btn_back_home_from_post = findViewById<Button>(R.id.btn_back_home_from_post)
        btn_back_home_from_post.setOnClickListener{
            devolverRespuesta()
        }

        var lastPost = BBaseDeDatosMemoria.arregloPosts.elementAt(0)
        var last_id_post = Integer.parseInt(lastPost.id_post)
        last_id_post += 1

        Log.i("LAST-ID","${last_id_post}")

        val txtIn_post_content = findViewById<TextInputEditText>(R.id.txtIn_post_postcontent)

        var btn_submit_post = findViewById<Button>(R.id.btn_post_postear)
        btn_submit_post.setOnClickListener {
            var post_content = txtIn_post_content.text
            BBaseDeDatosMemoria.arregloPosts.add(
                0,BPost(last_id_post.toString(),active_user.profilePic,active_user.name,active_user.username,"1seg", post_content.toString(), "","","",active_user.id_persona)
            )
            devolverRespuesta()
        }


    }

    fun devolverRespuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("active-user",active_user)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }

}