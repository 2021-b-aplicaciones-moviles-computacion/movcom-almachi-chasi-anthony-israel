package com.example.examen_1b

class BBaseDeDatosMemoria {

    companion object{
        var arregloEntrenador = arrayListOf<BEntrenador>()
        var arregloPokemon = arrayListOf<BPokemon>()
        var arregloEntrenadorXPokemon = arrayListOf<BEntrenadorXPokemon>()

        init {
            // cargar datos entrenador
            arregloEntrenador.add(
                BEntrenador(1,"Anthony","23")
            )
            arregloEntrenador.add(
                BEntrenador(2,"Bladimir","22")
            )
            arregloEntrenador.add(
                BEntrenador(3,"Alexis","21")
            )

            // cargar datos pokemon
            arregloPokemon.add(
                BPokemon(1,"Pikachu","Electrico")
            )
            arregloPokemon.add(
                BPokemon(2,"Squerel","Agua")
            )
            arregloPokemon.add(
                BPokemon(3,"Bolbasor","Hoja")
            )

            // cargar datos entrenador x pokemon
            arregloEntrenadorXPokemon.add(
                BEntrenadorXPokemon(1,2)
            )
            arregloEntrenadorXPokemon.add(
                BEntrenadorXPokemon(1, 3)
            )
            arregloEntrenadorXPokemon.add(
                BEntrenadorXPokemon(2, 3)
            )
            arregloEntrenadorXPokemon.add(
                BEntrenadorXPokemon(2,1)
            )
            arregloEntrenadorXPokemon.add(
                BEntrenadorXPokemon(2,2)
            )
            arregloEntrenadorXPokemon.add(
                BEntrenadorXPokemon(3,2)
            )

        }

    }

}