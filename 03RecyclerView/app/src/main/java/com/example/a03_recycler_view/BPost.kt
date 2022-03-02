package com.example.a03_recycler_view

import android.os.Parcel
import android.os.Parcelable

class BPost(
    val id_post:String?,
    val user_profile_pic:Int,
    val user_name:String?,
    val user_username:String?,
    val hour:String?,
    val post_content:String?,
    var action_comment_count:String?,
    var action_retwet_count: String?,
    var action_like_count: String?,
    var current_user: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun toString(): String {
        return "${user_username}"+"\t"+"${post_content}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id_post)
        parcel.writeInt(user_profile_pic)
        parcel.writeString(user_name)
        parcel.writeString(user_username)
        parcel.writeString(hour)
        parcel.writeString(post_content)
        parcel.writeString(action_comment_count)
        parcel.writeString(action_retwet_count)
        parcel.writeString(action_like_count)
        parcel.writeString(current_user)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BPost> {
        override fun createFromParcel(parcel: Parcel): BPost {
            return BPost(parcel)
        }

        override fun newArray(size: Int): Array<BPost?> {
            return arrayOfNulls(size)
        }
    }

}