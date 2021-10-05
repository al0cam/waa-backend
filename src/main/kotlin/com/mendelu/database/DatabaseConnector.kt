package com.mendelu.database

import com.mendelu.mappers.ContactMapper
import com.mendelu.mappers.LocationMapper
import com.mendelu.mappers.PersonMapper
import com.mendelu.mappers.RelationMapper
import org.apache.ibatis.mapping.Environment
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource
import org.apache.ibatis.session.Configuration
import org.apache.ibatis.session.SqlSessionFactory
import org.apache.ibatis.session.SqlSessionFactoryBuilder
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory

object DatabaseConnector {
    var  sqlSessionFactory: SqlSessionFactory
    init {
        val JDBC_URL = "jdbc:postgresql://hattie.db.elephantsql.com:5432/ljoeqgtl"
        val JDBC_USER = "ljoeqgtl"
        val JDBC_PASSWORD = "eS7f4RtRtYDtaiWQZJTpUJjzxqOAPlFn"
        val JDBC_DRIVER = "org.postgresql.Driver"
        val ds = UnpooledDataSource(JDBC_DRIVER, JDBC_URL, JDBC_USER, JDBC_PASSWORD)
        val environment = Environment("dev", JdbcTransactionFactory(),ds)
        val config = Configuration (environment)
        config.addMapper(PersonMapper::class.java)
        config.addMapper(LocationMapper::class.java)
        config.addMapper(ContactMapper::class.java)
        config.addMapper(RelationMapper::class.java)
        sqlSessionFactory = SqlSessionFactoryBuilder().build(config)
    }
}