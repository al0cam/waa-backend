package com.mendelu.mappers

import com.mendelu.domain.Person
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

interface PersonMapper {
    @Select(
        "SELECT *",
        "FROM person order by id_person")
    fun findAll():List<Person>

    @Select(
        "select *",
        "from person where id_person = #{value}"
    )
    fun findById(id: Int): Person

    @Insert("insert into person (nickname, first_name, last_name, id_location, birth_day, height, gender) " +
            "values(#{nickname}, #{firstname}, #{lastname},#{location}, #{birthday},#{height}, #{gender})")
    fun insertPerson(person: Person):Int?

    @Update("update person " +
            "set nickname = #{nickname}, first_name = #{firstname}," +
            " last_name = #{lastname}, height = #{height}," +
            " birth_day = #{birthday}, id_location = #{location}, gender = #{gender}" +
            " where id_person = #{idPerson}")
    fun updatePerson(person: Person):Int?

    @Delete("delete", "from person where id_person = #{value}")
    fun deletePerson(id: Int):Int?
}