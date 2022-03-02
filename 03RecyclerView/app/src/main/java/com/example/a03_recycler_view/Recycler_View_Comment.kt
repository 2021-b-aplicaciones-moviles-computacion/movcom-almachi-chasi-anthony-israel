package com.example.a03_recycler_view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class Recycler_View_Comment(
    private val contexto:GUI_Comment,
    private val listaComment: List<BComment>,
    private val recyclerView: RecyclerView
) : RecyclerView.Adapter<Recycler_View_Comment.MyViewHolder> () {

    inner class MyViewHolder(view:View):RecyclerView.ViewHolder(view){
        val comment_id: TextView
        val post_id: TextView
        var user_profile_pic: ImageView
        val user_name_TextView: TextView
        val user_username_TextView: TextView
        val hour_TextView: TextView
        val post_content_TextView: TextView
        val action_comment_ImageView: ImageView
        var comment_count_TextView: TextView
        var number_comments = 0
        val action_retwet_ImageView: ImageView
        var retwet_count_TextView: TextView
        var number_retwets = 0
        val action_like_ImageView: ImageView
        var like_count_TextView: TextView
        var number_likes = 0
        val action_share_ImageView: ImageView
        val current_user_TextView: TextView
        init {
            comment_id = view.findViewById(R.id.card_view_comment_id_comment)
            post_id = view.findViewById(R.id.card_view_comment_id_post)
            user_profile_pic = view.findViewById(R.id.card_view_comment_user_profile_pic)
            user_name_TextView = view.findViewById(R.id.card_view_comment_user_name)
            user_username_TextView = view.findViewById(R.id.card_view_comment_user_username)
            hour_TextView = view.findViewById(R.id.card_view_comment_hour)
            post_content_TextView = view.findViewById(R.id.card_view_comment_text_content)
            action_comment_ImageView = view.findViewById(R.id.card_view_comment_action_comment)
            action_comment_ImageView.setOnClickListener{
                this.increaseNumberComment()
            }
            comment_count_TextView = view.findViewById(R.id.card_view_comment_cont_comment)
            action_retwet_ImageView = view.findViewById(R.id.card_view_comment_action_retwet)
            action_retwet_ImageView.setOnClickListener{
                this.increaseNumberRetwet()
            }
            retwet_count_TextView = view.findViewById(R.id.card_view_comment_cont_retwet)
            action_like_ImageView = view.findViewById(R.id.card_view_comment_action_like)
            action_like_ImageView.setOnClickListener {
                this.increaseNumberLike()
            }
            like_count_TextView = view.findViewById(R.id.card_view_comment_cont_like)
            action_share_ImageView = view.findViewById(R.id.card_view_comment_action_share)
            current_user_TextView = view.findViewById(R.id.card_view_comment_id_current_user)
        }

        fun increaseNumberComment(){
            var idComment = comment_id.text
            var current_Comment = BComment("","",1,"","","","","","","","")
            BBaseDeDatosMemoria.arregloComment.forEach { comment : BComment ->
                if(comment.id_post == idComment){
                    current_Comment = comment
                }
            }

            val intentHomeToComment = Intent(contexto, GUI_Comment::class.java)
            intentHomeToComment.putExtra("current-post", current_Comment)
            ContextCompat.startActivity(contexto, intentHomeToComment, Bundle())
        }

        fun increaseNumberRetwet(){
            this.number_retwets = this.number_retwets + 1
            var idComment = comment_id.text
            var current_comment = BComment("","",1,"","","","","","","","")
            var current_actionPerUserComment = BActionPerUserComment("","",0,0)

            var found_action_user = 0
            BBaseDeDatosMemoria.arregloComment.forEach { comment : BComment ->
                if(comment.id_comment == idComment){

                    current_comment = comment

                    BBaseDeDatosMemoria.arregloActionPerUserComment.forEach { actionXuserComment:BActionPerUserComment ->
                        if ( current_comment.id_comment.equals(actionXuserComment.id_coment) &&  current_comment.current_user.equals(actionXuserComment.id_persona)){
                            current_actionPerUserComment = actionXuserComment
                            found_action_user = 1
                        }
                    }
                    if (found_action_user == 0){
                        current_actionPerUserComment.id_persona = current_comment.current_user
                        current_actionPerUserComment.id_coment = current_comment.id_comment
                        current_actionPerUserComment.action_like = 0
                        current_actionPerUserComment.action_retwet = 0
                        BBaseDeDatosMemoria.arregloActionPerUserComment.add(current_actionPerUserComment)
                    }

                    current_actionPerUserComment.action_retwet += 1

                    var count = 0
                    if(!comment.action_retwet_count.equals("")) count = Integer.parseInt(comment.action_retwet_count)
                    if(current_actionPerUserComment.action_retwet == 1){
                        count = count + 1
                        action_retwet_ImageView.setImageResource(R.drawable.ic_retweeted)
                    }else {
                        count = count - 1
                        action_retwet_ImageView.setImageResource(R.drawable.ic_retweet)
                        current_actionPerUserComment.action_retwet = 0
                    }
                    Log.i("RETWET","${idComment} \t ${count}")
                    if(count == 0){
                        comment.action_retwet_count = ""
                    }else{
                        comment.action_retwet_count = count.toString()
                    }
                }
            }
            retwet_count_TextView.text = current_comment.action_retwet_count
        }

        fun increaseNumberLike(){
            var idComment = comment_id.text
            var current_Comment = BComment("","",1,"","","","","","","","")
            var current_actionPerUserComment = BActionPerUserComment("","",0,0)

            var found_action_user = 0
            BBaseDeDatosMemoria.arregloComment.forEach { comment : BComment ->
                if(comment.id_comment == idComment){

                    current_Comment = comment

                    BBaseDeDatosMemoria.arregloActionPerUserComment.forEach { actionXuserComment:BActionPerUserComment ->
                        if ( current_Comment.id_comment.equals(actionXuserComment.id_coment) &&  current_Comment.current_user.equals(actionXuserComment.id_persona)){
                            current_actionPerUserComment = actionXuserComment
                            found_action_user = 1
                        }
                    }
                    if (found_action_user == 0){
                        current_actionPerUserComment.id_persona = current_Comment.current_user
                        current_actionPerUserComment.id_coment = current_Comment.id_comment
                        current_actionPerUserComment.action_like = 0
                        current_actionPerUserComment.action_retwet = 0
                        BBaseDeDatosMemoria.arregloActionPerUserComment.add(current_actionPerUserComment)
                    }

                    current_actionPerUserComment.action_like += 1

                    var count = 0
                    if(!comment.action_like_count.equals("")) count = Integer.parseInt(comment.action_like_count)
                    if(current_actionPerUserComment.action_like== 1){
                        count = count + 1
                        action_like_ImageView.setImageResource(R.drawable.ic_liked)
                    }else {
                        count = count - 1
                        action_like_ImageView.setImageResource(R.drawable.ic_like)
                        current_actionPerUserComment.action_like = 0
                    }
                    Log.i("LIKE","${idComment} \t ${count}")
                    if(count == 0){
                        comment.action_like_count = ""
                    }else{
                        comment.action_like_count = count.toString()
                    }
                }
            }
            like_count_TextView.text = current_Comment.action_like_count
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Recycler_View_Comment.MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.card_view_recycler_view_comment,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: Recycler_View_Comment.MyViewHolder, position: Int) {
        val comment = listaComment[position]
        holder.comment_id.text = comment.id_comment
        holder.post_id.text = comment.id_post
        holder.user_profile_pic.setImageResource(comment.user_profile_pic)
        holder.user_name_TextView.text = comment.user_name
        holder.user_username_TextView.text = comment.user_username
        holder.hour_TextView.text = comment.hour
        holder.post_content_TextView.text = comment.post_content
        holder.action_comment_ImageView
        holder.comment_count_TextView.text = comment.action_comment_count
        holder.retwet_count_TextView.text = comment.action_retwet_count
        holder.like_count_TextView.text = comment.action_like_count
        holder.action_share_ImageView
        holder.current_user_TextView.text = comment.current_user

        BBaseDeDatosMemoria.arregloActionPerUserComment.forEach { actionXuserComent:BActionPerUserComment ->
            if(holder.current_user_TextView.text.equals(actionXuserComent.id_persona) && holder.comment_id.text.equals(actionXuserComent.id_coment)){
                if(actionXuserComent.action_like == 1){
                    holder.action_like_ImageView.setImageResource(R.drawable.ic_liked)
                }
                if(actionXuserComent.action_retwet == 1){
                    holder.action_retwet_ImageView.setImageResource(R.drawable.ic_retweeted)
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return listaComment.size
    }

}