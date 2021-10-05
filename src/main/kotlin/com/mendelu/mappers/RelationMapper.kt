package com.mendelu.mappers

import com.mendelu.domain.Relation
import com.mendelu.domain.Person
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

interface RelationMapper {
    @Select("select *",
            "from relation")
    fun findAll():List<Relation>
    // join relation_type as ct on c.id_relation_type = ct.id_relation_type
    //select c.id_relation, c.id_person, ct.name, c.relation from relation as c, relation_type as ct where c.id_relation_type = ct.id_relation_type
    //select c.id_relation, c.id_person, ct.name, c.relation from relation as c join relation_type as ct on c.id_relation_type = ct.id_relation_type

    @Select(
        "select *",
        "from relation as c where c.id_relation = #{value}"
    )
    fun findById(id: Int): Relation

    @Select(
        "select *",
        "from relation as c where c.id_person1 = #{value}"
    )
    fun findAllByPersonId(id: Int): List<Relation>

    @Insert("insert into relation(id_person1, id_person2, description, id_relation_type) " +
            "values(#{idPerson1}, #{idPerson2}, #{description}, #{idRelationType})")
    fun insert(relation: Relation):Int?

    @Update("update relation " +
            "set id_person1 = #{idPerson1}, id_person2 = #{idPerson2}, description = #{description} " +
            ", id_relation_type =  #{idRelationType}" +
            " where id_relation = #{idRelation}")
    fun update(relation: Relation):Int?

    @Delete("delete", "from relation where id_relation = #{value}")
    fun delete(id: Int):Int?
}