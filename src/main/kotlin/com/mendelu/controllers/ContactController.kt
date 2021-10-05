package com.mendelu.controllers

import com.mendelu.database.DatabaseConnector
import com.mendelu.domain.Contact
import com.mendelu.mappers.ContactMapper
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*


@Controller("/contacts")
class ContactController {

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    fun index():List<Contact>{
        DatabaseConnector.sqlSessionFactory.openSession().use { session ->
            val mapper = session.getMapper(ContactMapper::class.java)
            return mapper.findAll()
        }
    }

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getContactById(@PathVariable  id: Int): List<Contact> {
        DatabaseConnector.sqlSessionFactory.openSession().use { session ->
            val mapper = session.getMapper(ContactMapper::class.java)
            return mapper.findAllByPersonId(id)
        }
    }

    @Post()
    fun createContact(@Body contact: Contact):HttpResponse<*>{
        DatabaseConnector.sqlSessionFactory.openSession().use { session ->
            val mapper = session.getMapper(ContactMapper::class.java)
            mapper.insert(contact)
            session.commit()
        }
        return HttpResponse.ok<Any>()
    }

    @Put
    fun updateContact(@Body contact: Contact):HttpResponse<*>{
        DatabaseConnector.sqlSessionFactory.openSession().use { session ->
            val mapper = session.getMapper(ContactMapper::class.java)
            mapper.update(contact)
            session.commit()
        }
        return HttpResponse.ok<Any>()
    }


    @Delete("/{id}")
    fun deleteContact(@PathVariable id: Int):HttpResponse<*>{
        DatabaseConnector.sqlSessionFactory.openSession().use { session ->
            val mapper = session.getMapper(ContactMapper::class.java)
            mapper.delete(id)
            session.commit()
        }
        return HttpResponse.ok<Any>()
    }
}