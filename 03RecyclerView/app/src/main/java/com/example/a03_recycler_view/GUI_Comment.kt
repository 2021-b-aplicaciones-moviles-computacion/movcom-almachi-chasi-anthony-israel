package com.example.a03_recycler_view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText

class GUI_Comment : AppCompatActivity() {

    var active_user = BPersona("", 0, "", "", "", "", "")
    var active_post = BPost("",1,"","","","","","","","")
    var list_comments = arrayListOf<BComment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gui_comment)
    }

    override fun onStart() {
        super.onStart()

        active_post = intent.getParcelableExtra<BPost>("current-post")!!

        val iv_profile_pic = findViewById<ImageView>(R.id.iv_comment_profile_pic)
        iv_profile_pic.setImageResource(active_post.user_profile_pic)

        val tv_user_name = findViewById<TextView>(R.id.tv_comment_name)
        tv_user_name.text = active_post.user_name

        val tv_user_username = findViewById<TextView>(R.id.tv_comment_username)
        tv_user_username.text = active_post.user_username

        val tv_post_content = findViewById<TextView>(R.id.tx_comment_post_content)
        tv_post_content.text = active_post.post_content

        val tv_number_comments = findViewById<TextView>(R.id.comment_cont_comment)
        tv_number_comments.text = active_post.action_comment_count

        val tv_number_retwet = findViewById<TextView>(R.id.tv_comment_cont_retwet)
        tv_number_retwet.text = active_post.action_retwet_count

        val tv_number_likes = findViewById<TextView>(R.id.tv_comment_cont_like)
        tv_number_likes.text = active_post.action_like_count

        BBaseDeDatosMemoria.arregloPersona.forEach { persona:BPersona ->
            if(active_post.current_user.equals(persona.id_persona)){
                active_user = persona
            }
        }

        BBaseDeDatosMemoria.arregloComment.forEach { coment : BComment ->
            if(active_post.id_post.equals(coment.id_post)){
                list_comments.add(coment)
            }
        }

        val recyclerViewComment = findViewById<RecyclerView>(R.id.rv_comments_container)
        BBaseDeDatosMemoria.arregloComment.forEach { coment : BComment ->
            coment.current_user = active_user.id_persona
        }
        inicializarRecyclerView(list_comments, this, recyclerViewComment)

        val btn_backHome_from_Comment = findViewById<ImageButton>(R.id.btn_backhome_from_comment)
        btn_backHome_from_Comment.setOnClickListener {
            abrirGUI_HomeConParametros(GUI_Home::class.java)
        }

        var num_of_comments = BBaseDeDatosMemoria.arregloComment.size
        num_of_comments -= 1
        var last_comment = BBaseDeDatosMemoria.arregloComment.elementAt(num_of_comments)
        var next_id = Integer.parseInt(last_comment.id_comment)
        next_id += 1

        var txtIn_comment_content = findViewById<TextInputEditText>(R.id.txtIn_comment_comment_content)

        val btn_add_comment = findViewById<Button>(R.id.btn_comment_add_comment)
        btn_add_comment.setOnClickListener {
            BBaseDeDatosMemoria.arregloPosts.forEach { post:BPost ->
                if(active_post.id_post == post.id_post){
                    var num_comments = -1
                    if(post.action_comment_count.equals("")){
                        num_comments = 0
                    }else {
                        num_comments = Integer.parseInt(post.action_comment_count)
                    }
                    num_comments += 1
                    post.action_comment_count = num_comments.toString()
                    tv_number_comments.text = post.action_comment_count
                }
            }

            var comment_text_content = txtIn_comment_content.text

            var comment_element_to_add = BComment(next_id.toString(),active_post.id_post,active_user.profilePic,active_user.name,active_user.username,"1seg",comment_text_content.toString(),"","","",active_user.id_persona)

            BBaseDeDatosMemoria.arregloComment.add(comment_element_to_add)
            list_comments.add(comment_element_to_add)

            inicializarRecyclerView(list_comments, this, recyclerViewComment)

            txtIn_comment_content.setText("")

            Toast.makeText(this,"Comentario añadido", Toast.LENGTH_SHORT).show()

        }

    }

    fun inicializarRecyclerView(
        listaComents: List<BComment>,
        actividad: GUI_Comment,
        recyclerView: RecyclerView
    ) {
        val adaptador = Recycler_View_Comment(
            actividad,
            listaComents,
            recyclerView
        )

        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)
        adaptador.notifyDataSetChanged()

    }

    fun abrirGUI_HomeConParametros(
        clase: Class<*>
    ) {
        val intentAddNewPost = Intent(this, clase)
        intentAddNewPost.putExtra("active-user", active_user)
        startActivity(intentAddNewPost)
    }

}