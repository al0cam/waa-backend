package com.mendelu.domain

data class Contact (
    val idContact: Long, val idPerson: Long,
    val idContactType: Long, val contact: String)