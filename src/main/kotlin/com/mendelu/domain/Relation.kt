package com.mendelu.domain

data class Relation (
    val idRelation: Long, val idPerson1: Long, val idPerson2: Long,
    val description: String, val idRelationType: Long)