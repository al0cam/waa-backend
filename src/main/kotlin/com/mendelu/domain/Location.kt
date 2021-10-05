package com.mendelu.domain

data class Location(
    val idLocation: Int, val city: String?,
    val streetName: String?, val streetNumber: Int?,
    val zip: String?, val country: String?, val placeName: String?,
    val latitude: Double?, val longitude: Double?
)