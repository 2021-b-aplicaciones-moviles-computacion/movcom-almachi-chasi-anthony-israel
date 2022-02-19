package com.example.a03_recycler_view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class GUI_Home : AppCompatActivity() {

    var active_user = BPersona("", 0, "", "", "", "", "")
    var idItemSelected = 0

    var resultAddNewPost = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null) {
                val data = result.data
                active_user = data?.getParcelableExtra<BPersona>("active-user")!!
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gui_home)
    }

    override fun onStart() {
        super.onStart()

        active_user = intent.getParcelableExtra<BPersona>("active-user")!!

        val profile_pic = findViewById<ImageView>(R.id.home_user_profile_pic)
        profile_pic.setImageResource(active_user.profilePic)
        profile_pic.setOnClickListener {
            val popupMenu: PopupMenu = PopupMenu(this,profile_pic)
            popupMenu.menuInflater.inflate(R.menu.home_profile_menu,popupMenu.menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when(item.itemId) {
                    R.id.mi_home_profile ->
                        Toast.makeText(this@GUI_Home, "Not implemented yet", Toast.LENGTH_SHORT).show()
                    R.id.mi_home_lists ->
                        Toast.makeText(this@GUI_Home, "Not implemented yet", Toast.LENGTH_SHORT).show()
                    R.id.mi_home_themes ->
                        Toast.makeText(this@GUI_Home, "Not implemented yet", Toast.LENGTH_SHORT).show()
                    R.id.mi_home_save_elements ->
                        Toast.makeText(this@GUI_Home, "Not implemented yet", Toast.LENGTH_SHORT).show()
                    R.id.mi_home_moments ->
                        Toast.makeText(this@GUI_Home, "Not implemented yet", Toast.LENGTH_SHORT).show()
                    R.id.mi_home_logout -> {
                        val intent = Intent(this,MainActivity::class.java)
                        startActivity(intent)
                    }
                }
                true
            })
            popupMenu.show()
        }

        val recyclerViewPost = findViewById<RecyclerView>(R.id.rv_home_posts)
        BBaseDeDatosMemoria.arregloPosts.forEach { post:BPost ->
            post.current_user = active_user.id_persona
        }

        inicializarRecyclerView(BBaseDeDatosMemoria.arregloPosts, this, recyclerViewPost)

        val btn_post = findViewById<ImageButton>(R.id.btn_add_post)
        btn_post.setOnClickListener {
            abrirActividadConParametros(GUI_Post::class.java)
        }

        val btn_home_search = findViewById<ImageButton>(R.id.btn_home_search)
        btn_home_search.setOnClickListener {
            abrirActividadConParametros(GUI_Search::class.java)
        }

        val btn_home_notification = findViewById<ImageButton>(R.id.btn_home_notification)
        btn_home_notification.setOnClickListener {
            abrirActividadConParametros(GUI_Notification::class.java)
        }

        val btn_home_message = findViewById<ImageButton>(R.id.btn_home_messages)
        btn_home_message.setOnClickListener {
            abrirActividadConParametros(GUI_Message::class.java)
        }

    }

    fun inicializarRecyclerView(
        listaPosts: List<BPost>,
        actividad: GUI_Home,
        recyclerView: RecyclerView
    ) {
        val adaptador = Recycler_View_Post(
            actividad,
            listaPosts,
            recyclerView
        )

        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)
        adaptador.notifyDataSetChanged()

    }

    fun abrirActividadConParametros(
        clase: Class<*>
    ) {
        val intentAddNewPost = Intent(this, clase)
        intentAddNewPost.putExtra("active-user", active_user)
        resultAddNewPost.launch(intentAddNewPost)
    }

}