package com.mendelu.controllers

import com.mendelu.database.DatabaseConnector
import com.mendelu.domain.Location
import com.mendelu.mappers.LocationMapper
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*

@Controller("/locations")
class LocationController {
    @Get
    @Produces(MediaType.APPLICATION_JSON)
    fun index():List<Location>{
        DatabaseConnector.sqlSessionFactory.openSession().use { session ->
            val mapper = session.getMapper(LocationMapper::class.java)
            return mapper.findAll()
        }
    }

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getPersonById(@PathVariable id: Int): Location {
        DatabaseConnector.sqlSessionFactory.openSession().use { session ->
            val mapper = session.getMapper(LocationMapper::class.java)
            return mapper.findById(id)
        }
    }

    @Post()
    fun createPerson(@Body location: Location): HttpResponse<*> {
        DatabaseConnector.sqlSessionFactory.openSession().use { session ->
            val mapper = session.getMapper(LocationMapper::class.java)
            mapper.insert(location)
            session.commit()
        }
        return HttpResponse.ok<Any>()
    }

    @Put
    fun updatePerson(@Body location: Location): HttpResponse<*> {
        DatabaseConnector.sqlSessionFactory.openSession().use { session ->
            val mapper = session.getMapper(LocationMapper::class.java)
            mapper.update(location)
            session.commit()
        }
        return HttpResponse.ok<Any>()
    }


    @Delete("/{id}")
    fun delete(@PathVariable id: Int): HttpResponse<*> {
        DatabaseConnector.sqlSessionFactory.openSession().use { session ->
            val mapper = session.getMapper(LocationMapper::class.java)
            mapper.delete(id)
            session.commit()
        }
        return HttpResponse.ok<Any>()
    }
}