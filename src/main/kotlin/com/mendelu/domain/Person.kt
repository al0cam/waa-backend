package com.mendelu.domain

import java.sql.Date

data class Person (
    val idPerson:Int, val nickname:String, val firstname:String,
    val lastname:String, val location: Long?, var birthday:Date?,
    val height:Int?, val gender:String? )
