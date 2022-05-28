package com.example.showcoins.model

import com.google.gson.annotations.SerializedName

data class Coin(val name: String, @SerializedName("price")val price_usd: String)