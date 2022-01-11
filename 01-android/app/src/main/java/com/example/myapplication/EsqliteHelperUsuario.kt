package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class EsqliteHelperUsuario (
    contexto: Context?
): SQLiteOpenHelper(
    contexto,
    "moveiles",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptCrearTablaUsuario =
            """
                CREATE TABLE USUARIO (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre Varchar(50),
                descipcion varchar(50)
                )
            """.trimIndent()

        Log.i("bbd","Creando la tabla usuario")
        db?.execSQL(scriptCrearTablaUsuario)
    }

    fun crearUsuarioFormulario(
        nombre: String,
        descripcion: String
    ): Boolean{
        val baseDatosLectura = readableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("descipcion", descripcion)
        var resultadoEscritura: Long = baseDatosLectura
            .insert(
                "USUARIO",
                null,
                valoresAGuardar
            )
        baseDatosLectura.close()
        return if(resultadoEscritura.toInt()==-1) false else true
    }


    fun consultarUsuarioPorId(id: Int): EUsuarioBDD{
        val baseDatosLectura = readableDatabase
        val scriptConsultarUsuario = "SELECT * FROM USUARIO WHERE ID=${id}"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarUsuario,
            null
        )
        val existeUsuario = resultadoConsultaLectura.moveToFirst()
        val usuarioEncontrado = EUsuarioBDD(0,"","")
        if (existeUsuario){
            do{
                val id = resultadoConsultaLectura.getInt(0) // columna indice 0 -> ID
                val nombre = resultadoConsultaLectura.getString(1) // columna indice 1 -> nombre
                val desripcion = resultadoConsultaLectura.getString(2) // columna indice 2 - > descipcion

                if(id!=null){
                    usuarioEncontrado.id = id
                    usuarioEncontrado.nombre = nombre
                    usuarioEncontrado.descripcion = desripcion
                }
            }while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return usuarioEncontrado
    }

    fun eliminarUsuarioFormulario(id: Int): Boolean{
        val conexionEscritura = writableDatabase
        val resultadoEliminacion = conexionEscritura
            .delete(
                "USUARIO",
                "id=?",
                arrayOf(
                    id.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }

    fun actualizarUsuarioFormulario(
        nombre: String,
        descripcion: String,
        idActualizar: Int
    ): Boolean{
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("descipcion", descripcion)
        val resultadoAtualizacion = conexionEscritura
            .update(
                "USUARIO",
                valoresAActualizar,
                "id=?",
                arrayOf(
                    idActualizar.toString()
                )
            )
        conexionEscritura.close()
        return if(resultadoAtualizacion == -1) false else true
    }

    override fun onUpgrade(p1: SQLiteDatabase?, p2: Int, p3: Int) {
        TODO("Not yet implemented")
    }
}