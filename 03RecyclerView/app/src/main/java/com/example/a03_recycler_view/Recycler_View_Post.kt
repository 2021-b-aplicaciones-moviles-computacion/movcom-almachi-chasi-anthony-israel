package com.example.a03_recycler_view

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class Recycler_View_Post(
    private val contexto:GUI_Home,
    private val listaPost: List<BPost>,
    private val recyclerView:RecyclerView
) : RecyclerView.Adapter<Recycler_View_Post.MyViewHolder> () {

    inner class MyViewHolder(view:View):RecyclerView.ViewHolder(view){
        val post_id:TextView
        var user_profile_pic:ImageView
        val user_name_TextView:TextView
        val user_username_TextView:TextView
        val hour_TextView:TextView
        val post_content_TextView:TextView
        val action_comment_ImageView:ImageView
        var comment_count_TextView:TextView
        var number_comments = 0
        val action_retwet_ImageView:ImageView
        var retwet_count_TextView: TextView
        var number_retwets = 0
        val action_like_ImageView:ImageView
        var like_count_TextView: TextView
        var number_likes = 0
        val action_share_ImageView: ImageView
        val current_user_TextView: TextView
        init {
            post_id = view.findViewById(R.id.card_view_id_card)
            user_profile_pic = view.findViewById(R.id.card_view_user_profile_pic)
            user_name_TextView = view.findViewById(R.id.card_view_user_name)
            user_username_TextView = view.findViewById(R.id.card_view_user_username)
            hour_TextView = view.findViewById(R.id.card_view_hour)
            post_content_TextView = view.findViewById(R.id.card_view_text_content)
            action_comment_ImageView = view.findViewById(R.id.card_view_action_comment)
            action_comment_ImageView.setOnClickListener{
                this.increaseNumberComment()
            }
            comment_count_TextView = view.findViewById(R.id.card_view_cont_comment)
            action_retwet_ImageView = view.findViewById(R.id.card_view_action_retwet)
            action_retwet_ImageView.setOnClickListener{
                this.increaseNumberRetwet()
            }
            retwet_count_TextView = view.findViewById(R.id.card_view_cont_retwet)
            action_like_ImageView = view.findViewById(R.id.card_view_action_like)
            action_like_ImageView.setOnClickListener {
                this.increaseNumberLike()
            }
            like_count_TextView = view.findViewById(R.id.card_view_cont_like)
            action_share_ImageView = view.findViewById(R.id.card_view_action_share)
            current_user_TextView = view.findViewById(R.id.card_view_id_current_user)

        }

        fun increaseNumberComment(){
            var idPost = post_id.text
            var current_post = BPost("",1,"","","","","","","","")
            BBaseDeDatosMemoria.arregloPosts.forEach { post : BPost ->
                if(post.id_post == idPost){
                    current_post = post
                }
            }
            val intentHomeToComment = Intent(contexto, GUI_Comment::class.java)
            intentHomeToComment.putExtra("current-post", current_post)
            startActivity(contexto,intentHomeToComment,Bundle())
        }

        fun increaseNumberRetwet(){
            var idPost = post_id.text
            var current_post = BPost("",1,"","","","","","","","")
            var current_actionPerUser = BActionsPerUser("","",0,0)
            var found_action_user = 0
            BBaseDeDatosMemoria.arregloPosts.forEach { post : BPost ->
                if(post.id_post == idPost){

                    current_post = post

                    BBaseDeDatosMemoria.arregloActionPerUser.forEach { actionXuser ->
                        if ( current_post.id_post.equals(actionXuser.id_post) &&  current_post.current_user.equals(actionXuser.id_persona)){
                            current_actionPerUser = actionXuser
                            found_action_user = 1
                        }
                    }
                    if (found_action_user == 0){
                        current_actionPerUser.id_persona = current_post.current_user
                        current_actionPerUser.id_post = current_post.id_post
                        current_actionPerUser.action_like = 0
                        current_actionPerUser.action_retwet = 0
                        BBaseDeDatosMemoria.arregloActionPerUser.add(current_actionPerUser)
                    }

                    current_actionPerUser.action_retwet += 1

                    var count_total_retwets = 0
                    if(!post.action_retwet_count.equals("")) count_total_retwets = Integer.parseInt(post.action_retwet_count)
                    if(current_actionPerUser.action_retwet == 1){
                        count_total_retwets = count_total_retwets + 1
                        action_retwet_ImageView.setImageResource(R.drawable.ic_retweeted)
                    }else {
                        count_total_retwets = count_total_retwets - 1
                        action_retwet_ImageView.setImageResource(R.drawable.ic_retweet)
                        current_actionPerUser.action_retwet= 0
                    }
                    Log.i("RETWET","${idPost} \t ${count_total_retwets}")
                    if(count_total_retwets == 0){
                        post.action_retwet_count = ""
                    }else{
                        post.action_retwet_count = count_total_retwets.toString()
                    }
                }
            }
            retwet_count_TextView.text = current_post.action_retwet_count
        }

        fun increaseNumberLike(){
            //this.number_likes = this.number_likes + 1
            var idPost = post_id.text
            var current_post = BPost("",1,"","","","","","","","")
            var current_actionPerUser = BActionsPerUser("","",0,0)

            var found_action_user = 0
            BBaseDeDatosMemoria.arregloPosts.forEach { post : BPost ->
                if(post.id_post == idPost){
                    current_post = post
                    BBaseDeDatosMemoria.arregloActionPerUser.forEach { actionXuser ->
                        if ( current_post.id_post.equals(actionXuser.id_post) &&  current_post.current_user.equals(actionXuser.id_persona)){
                            current_actionPerUser = actionXuser
                            found_action_user = 1
                        }
                    }
                    if (found_action_user == 0){
                        current_actionPerUser.id_persona = current_post.current_user
                        current_actionPerUser.id_post = current_post.id_post
                        current_actionPerUser.action_like = 0
                        current_actionPerUser.action_retwet = 0
                        BBaseDeDatosMemoria.arregloActionPerUser.add(current_actionPerUser)
                    }

                    current_actionPerUser.action_like += 1
                    var count_total_likes = 0
                    if(!post.action_like_count.equals("")) count_total_likes = Integer.parseInt(post.action_like_count)
                    //Log.i("TEST-NEW-LIKE","mikasa_likes ${this.number_likes} total_likes ${count_total_likes}")
                    if(current_actionPerUser.action_like == 1){
                        count_total_likes = count_total_likes + 1
                        action_like_ImageView.setImageResource(R.drawable.ic_liked)
                    }else {
                        count_total_likes = count_total_likes - 1
                        action_like_ImageView.setImageResource(R.drawable.ic_like)
                        current_actionPerUser.action_like = 0
                    }
                    Log.i("LIKE","${idPost} \t ${count_total_likes}")
                    if(count_total_likes == 0){
                        post.action_like_count = ""
                    }else{
                        post.action_like_count = count_total_likes.toString()
                    }
                }
            }
            like_count_TextView.text = current_post.action_like_count
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.card_view_recycler_view,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val post = listaPost[position]
        holder.post_id.text = post.id_post
        holder.user_profile_pic.setImageResource(post.user_profile_pic)
        holder.user_name_TextView.text = post.user_name
        holder.user_username_TextView.text = post.user_username
        holder.hour_TextView.text = post.hour
        holder.post_content_TextView.text = post.post_content
        holder.action_comment_ImageView
        holder.comment_count_TextView.text = post.action_comment_count
        holder.retwet_count_TextView.text = post.action_retwet_count
        holder.action_like_ImageView
        holder.like_count_TextView.text = post.action_like_count
        holder.action_share_ImageView
        holder.current_user_TextView.text = post.current_user

        BBaseDeDatosMemoria.arregloActionPerUser.forEach { actionXuser:BActionsPerUser ->
            if(holder.current_user_TextView.text.equals(actionXuser.id_persona) && holder.post_id.text.equals(actionXuser.id_post)){
                if(actionXuser.action_like == 1){
                    holder.action_like_ImageView.setImageResource(R.drawable.ic_liked)
                }
                if(actionXuser.action_retwet == 1){
                    holder.action_retwet_ImageView.setImageResource(R.drawable.ic_retweeted)
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return listaPost.size
    }

}