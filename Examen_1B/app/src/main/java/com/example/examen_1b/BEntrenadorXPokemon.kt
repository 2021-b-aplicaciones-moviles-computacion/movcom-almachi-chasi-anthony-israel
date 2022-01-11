package com.example.examen_1b

import android.os.Parcel
import android.os.Parcelable

class BEntrenadorXPokemon (

    val idEntrenador: Int,
    val idPokemon: Int

    ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idEntrenador)
        parcel.writeInt(idPokemon)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BEntrenadorXPokemon> {
        override fun createFromParcel(parcel: Parcel): BEntrenadorXPokemon {
            return BEntrenadorXPokemon(parcel)
        }

        override fun newArray(size: Int): Array<BEntrenadorXPokemon?> {
            return arrayOfNulls(size)
        }
    }
}