package com.example.exameniib

import android.os.Parcel
import android.os.Parcelable

class FirestorePokemon(
    var pokemonId: String?,
    var pokemonName: String?,
    var pokemonOwner: String?,
    var pokemonSpecie: String?,
    var pokemonType: String?
) :Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun toString(): String {
        return "${pokemonSpecie}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(pokemonId)
        parcel.writeString(pokemonName)
        parcel.writeString(pokemonOwner)
        parcel.writeString(pokemonSpecie)
        parcel.writeString(pokemonType)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FirestorePokemon> {
        override fun createFromParcel(parcel: Parcel): FirestorePokemon {
            return FirestorePokemon(parcel)
        }

        override fun newArray(size: Int): Array<FirestorePokemon?> {
            return arrayOfNulls(size)
        }
    }
}