package com.mendelu.mappers

import com.mendelu.domain.Contact
import com.mendelu.domain.Person
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

interface ContactMapper {
    @Select("select *",
            "from contact")
    fun findAll():List<Contact>
    // join contact_type as ct on c.id_contact_type = ct.id_contact_type
    //select c.id_contact, c.id_person, ct.name, c.contact from contact as c, contact_type as ct where c.id_contact_type = ct.id_contact_type
    //select c.id_contact, c.id_person, ct.name, c.contact from contact as c join contact_type as ct on c.id_contact_type = ct.id_contact_type

    @Select(
        "select *",
        "from contact as c where c.id_contact = #{value}"
    )
    fun findById(id: Int): Contact

    @Select(
        "select *",
        "from contact as c where c.id_person = #{value}"
    )
    fun findAllByPersonId(id: Int): List<Contact>

    @Insert("insert into contact(id_person, id_contact_type, contact) " +
            "values( #{idPerson}, #{idContactType}, #{contact})")
    fun insert(contact: Contact):Int?

    @Update("update contact " +
            "set id_contact_type = #{idContactType}, contact = #{contact}" +
            " where id_contact = #{idContact}")
    fun update(contact: Contact):Int?

    @Delete("delete", "from contact where id_contact = #{value}")
    fun delete(id: Int):Int?
}