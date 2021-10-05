package com.mendelu.controllers

import com.mendelu.database.DatabaseConnector
import com.mendelu.domain.Relation
import com.mendelu.mappers.RelationMapper
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*


@Controller("/relations")
class RelationController {

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    fun index():List<Relation>{
        DatabaseConnector.sqlSessionFactory.openSession().use { session ->
            val mapper = session.getMapper(RelationMapper::class.java)
            return mapper.findAll()
        }
    }

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getRelationById(@PathVariable  id: Int): List<Relation> {
        DatabaseConnector.sqlSessionFactory.openSession().use { session ->
            val mapper = session.getMapper(RelationMapper::class.java)
            return mapper.findAllByPersonId(id)
        }
    }

    @Post()
    fun createRelation(@Body relation: Relation):HttpResponse<*>{
        DatabaseConnector.sqlSessionFactory.openSession().use { session ->
            val mapper = session.getMapper(RelationMapper::class.java)
            mapper.insert(relation)
            session.commit()
        }
        return HttpResponse.ok<Any>()
    }

    @Put
    fun updateRelation(@Body relation: Relation):HttpResponse<*>{
        DatabaseConnector.sqlSessionFactory.openSession().use { session ->
            val mapper = session.getMapper(RelationMapper::class.java)
            mapper.update(relation)
            session.commit()
        }
        return HttpResponse.ok<Any>()
    }


    @Delete("/{id}")
    fun deleteRelation(@PathVariable id: Int):HttpResponse<*>{
        DatabaseConnector.sqlSessionFactory.openSession().use { session ->
            val mapper = session.getMapper(RelationMapper::class.java)
            mapper.delete(id)
            session.commit()
        }
        return HttpResponse.ok<Any>()
    }
}