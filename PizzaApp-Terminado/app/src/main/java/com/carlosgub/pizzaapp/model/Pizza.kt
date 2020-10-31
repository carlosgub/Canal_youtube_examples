package com.carlosgub.pizzaapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pizza(
    val image: Int,
    val name: String,
    val category: String,
    val price: Float
):Parcelable