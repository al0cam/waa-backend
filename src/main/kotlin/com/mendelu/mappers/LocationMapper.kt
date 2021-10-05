package com.mendelu.mappers

import com.mendelu.domain.Location
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

interface LocationMapper {
    @Select(
        "select *", "from location order by id_location")
    fun findAll(): List<Location>

    @Select(
        "select *",
        "from location where id_location = #{value}"
    )
    fun findById(id: Int): Location

    @Insert("insert into location (city, street_name, street_number, zip, country, name, latitude, longitude)" +
            "values(#{city}, #{streetName}, #{streetNumber}, #{zip}," +
            "#{country}, #{name}, #{latitude}, #{longitude})")
    fun insert(location: Location):Int?

    @Update("update location " +
            "set city = #{city}, street_name = #{streetName}, street_number = #{streetNumber}, zip = #{zip}," +
            "county = #{country}, name = #{name}, latitude = #{latitude}, longitude = #{longitude" +
            " where id_location = #{location}")
    fun update(location: Location):Int?

    @Delete("delete", "from person where id_location = #{value}")
    fun delete(id: Int):Int?
}