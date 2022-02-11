package com.example.a03_recycler_view

import java.util.LinkedList

class BBaseDeDatosMemoria {

    companion object{
        val arregloPersona = arrayListOf<BPersona>()
        val arregloPosts = arrayListOf<BPost>()
        val arregloComment = arrayListOf<BComment>()

        init {
            arregloPersona.add(
                BPersona("1",R.drawable.user1_profilepic,"user1","Mikasa","user1@gmail.com","0111111111","prueba1")
            )
            arregloPersona.add(
                BPersona("2",R.drawable.user2_profilepic,"user2","Eren","user2@gmail.com","0222222222","prueba2")
            )
            arregloPersona.add(
                BPersona("3",R.drawable.user3_profilepic,"user3","Armin","user3@gmail.com","0333333333","prueba3")
            )



            arregloPosts.add(
                BPost("3",R.drawable.user3_profilepic,"Armin","user3","1min","Hola estes es mi tercer tweet","","","","")
            )
            arregloPosts.add(
                BPost("2",R.drawable.user2_profilepic,"Eren","user2","5min","Hola estes es mi segundo tweet","1","","","")
            )
            arregloPosts.add(
                BPost("1",R.drawable.user1_profilepic,"Mikasa","user1","1h","Hola estes es mi primer tweet","4","","","")
            )


            arregloComment.add(
                BComment("1","1",R.drawable.user1_profilepic,"Mikasa","user1","1min","primer comentario","","","","")
            )
            arregloComment.add(
                BComment("2","1",R.drawable.user2_profilepic,"Eren","user2","1min","Segundo comentario","","","","")
            )
            arregloComment.add(
                BComment("3","1",R.drawable.user1_profilepic,"Mikasa","user1","1min","terceeeerr","","","","")
            )
            arregloComment.add(
                BComment("4","1",R.drawable.user3_profilepic,"Armin","user3","1min","cuartoo","","","","")
            )
            arregloComment.add(
                BComment("5","2",R.drawable.user3_profilepic,"Armin","user3","1min","otro comentario - otro post","","","","")
            )


        }

    }

}