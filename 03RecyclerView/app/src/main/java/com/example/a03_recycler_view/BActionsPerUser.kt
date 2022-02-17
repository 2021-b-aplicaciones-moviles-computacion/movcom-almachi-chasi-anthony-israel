package com.example.a03_recycler_view

import android.os.Parcel
import android.os.Parcelable

class BActionsPerUser(
    var id_persona: String?,
    var id_post: String?,
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

    override fun toString(): String {
        return "${id_persona}"+"\t"+"${id_post}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id_persona)
        parcel.writeString(id_post)
        parcel.writeInt(action_retwet)
        parcel.writeInt(action_like)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BActionsPerUser> {
        override fun createFromParcel(parcel: Parcel): BActionsPerUser {
            return BActionsPerUser(parcel)
        }

        override fun newArray(size: Int): Array<BActionsPerUser?> {
            return arrayOfNulls(size)
        }
    }

}