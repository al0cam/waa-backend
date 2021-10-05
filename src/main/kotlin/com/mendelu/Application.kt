package com.mendelu

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.micronaut.runtime.Micronaut.*


fun main(args: Array<String>) {
    build()
            .args(*args)
            .packages("com.mendelu")
            .start()
    @Get
    @Produces(MediaType.APPLICATION_JSON)
    fun index():String{
        //System.out.println("hello world")
        return "{\"message\":\"hello world\"}"
    }

}

