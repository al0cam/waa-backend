package com.mendelu.controllers

import com.mendelu.database.DatabaseConnector
import com.mendelu.domain.Person
import com.mendelu.mappers.PersonMapper
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import java.sql.Date
import java.text.SimpleDateFormat


@Controller("/persons")
class PersonController {

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    fun index():List<Person>{
        DatabaseConnector.sqlSessionFactory.openSession().use { session ->
            val mapper = session.getMapper(PersonMapper::class.java)
//            var items: List<Person> = mapper.findAll()
//            for (item in items)
//                println(item.birthday)
            return mapper.findAll()
        }
    }

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getPersonById(@PathVariable  id: Int): Person {
        DatabaseConnector.sqlSessionFactory.openSession().use { session ->
            val mapper = session.getMapper(PersonMapper::class.java)
            return mapper.findById(id)
        }
    }

    @Post()
    fun createPerson(@Body person: Person):HttpResponse<*>{
        DatabaseConnector.sqlSessionFactory.openSession().use { session ->
            val mapper = session.getMapper(PersonMapper::class.java)
            if (person.birthday != null) {
                var dates =  SimpleDateFormat("yyyy-mm-dd").parse(person.birthday.toString())
                person.birthday= Date(dates.getTime())
            }
            mapper.insertPerson(person)
            session.commit()
        }
        return HttpResponse.ok<Any>()
    }

    @Put
    fun updatePerson(@Body person: Person):HttpResponse<*>{
        DatabaseConnector.sqlSessionFactory.openSession().use { session ->
            val mapper = session.getMapper(PersonMapper::class.java)
            if (person.birthday != null) {
                var dates =  SimpleDateFormat("yyyy-mm-dd").parse(person.birthday.toString())
                person.birthday= Date(dates.getTime())
            }
            mapper.updatePerson(person)
            session.commit()
        }
        return HttpResponse.ok<Any>()
    }

    @Delete("/{id}")
    fun deletePerson(@PathVariable id: Int):HttpResponse<*>{
        DatabaseConnector.sqlSessionFactory.openSession().use { session ->
            val mapper = session.getMapper(PersonMapper::class.java)
            mapper.deletePerson(id)
            session.commit()
        }
        return HttpResponse.ok<Any>()
    }
}