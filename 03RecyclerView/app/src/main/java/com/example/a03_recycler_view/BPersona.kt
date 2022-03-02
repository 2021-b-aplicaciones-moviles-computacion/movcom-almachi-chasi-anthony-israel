package com.example.a03_recycler_view

import android.os.Parcel
import android.os.Parcelable
import android.provider.ContactsContract

class BPersona(
    val id_persona: String?,
    var profilePic: Int,
    var username: String?,
    var name: String?,
    var email: String?,
    var phone: String?,
    val password: String?
) :Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun toString(): String {
        return "${id_persona}"+"\t"+"${username}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id_persona)
        parcel.writeInt(profilePic)
        parcel.writeString(username)
        parcel.writeString(name)
        parcel.writeString(email)
        parcel.writeString(phone)
        parcel.writeString(password)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BPersona> {
        override fun createFromParcel(parcel: Parcel): BPersona {
            return BPersona(parcel)
        }

        override fun newArray(size: Int): Array<BPersona?> {
            return arrayOfNulls(size)
        }
    }


}