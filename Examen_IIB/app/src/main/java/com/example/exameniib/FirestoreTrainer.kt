package com.example.exameniib

import android.os.Parcel
import android.os.Parcelable

class FirestoreTrainer (
    var trainerId: String?,
    var trainerName: String?,
    var age: String?,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
    ) {
    }

    override fun toString(): String {
        return "${trainerName}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(trainerId)
        parcel.writeString(trainerName)
        parcel.writeString(age)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FirestoreTrainer> {
        override fun createFromParcel(parcel: Parcel): FirestoreTrainer {
            return FirestoreTrainer(parcel)
        }

        override fun newArray(size: Int): Array<FirestoreTrainer?> {
            return arrayOfNulls(size)
        }
    }

}