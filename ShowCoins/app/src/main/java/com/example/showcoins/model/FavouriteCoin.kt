package com.example.showcoins.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "favourite_coin_table")
data class FavouriteCoin(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val coinName: String,
    val coinPrice: String,
    var isFavourite: Boolean
) : Parcelable