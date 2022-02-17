package com.example.a03_recycler_view

import android.os.Parcel
import android.os.Parcelable

class BActionPerUserComment(
    var id_persona: String?,
    var id_coment: String?,
    var action_retwet: Int,
    var action_like: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id_persona)
        parcel.writeString(id_coment)
        parcel.writeInt(action_retwet)
        parcel.writeInt(action_like)
    }

    override fun toString(): String {
        return "${id_persona}"+"\t"+"${id_coment}"
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BActionPerUserComment> {
        override fun createFromParcel(parcel: Parcel): BActionPerUserComment {
            return BActionPerUserComment(parcel)
        }

        override fun newArray(size: Int): Array<BActionPerUserComment?> {
            return arrayOfNulls(size)
        }
    }
}